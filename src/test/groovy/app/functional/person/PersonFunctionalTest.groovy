package app.functional.person

import app.mockmvc.MockMvcFacade
import app.mockmvc.ResponseMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class PersonFunctionalTest extends Specification {

    @Autowired
    MockMvcFacade mockMvcFacade

    @Autowired
    PersonLifecycle personLifecycle

    def root = '/persons'

    def 'GET: if resource not exists - 404'() {
        expect:
        mockMvcFacade.get([url: "$root/1"])
                .andExpect(status().isNotFound())
    }

    def 'GET: if there is no person with the searched name - 400'() {
        when: 'search for the persons with name'
        def getByNameResponse = mockMvcFacade.get([url: "$root?name=FAKE"])
                .andExpect(status().isBadRequest())
                .andReturn()
        def errors = ResponseMapper.parseResponse(getByNameResponse).data.errors

        then: 'verify error messages'
        errors.message == ['There is no person with name FAKE']
    }

    def 'GET: find all persons with given name'() {
        given: 'create resources that would be further filtered'
        def name = 'X'
        def id1 = personLifecycle.create([name: name, surname: 'Y1']).id
        def id2 = personLifecycle.create([name: name, surname: 'Y2']).id
        def id3 = personLifecycle.create([name: name, surname: 'Y3']).id
        def id4 = personLifecycle.create([name: name, surname: 'Y4']).id
        personLifecycle.create([name: 'X1', surname: 'Y4'])
        personLifecycle.create([name: 'X2', surname: 'Y4'])
        personLifecycle.create([name: 'X3', surname: 'Y4'])

        when: 'filter resources by given name'
        def getByNameResponse = mockMvcFacade.get([url: "$root?name=$name"])
                .andExpect(status().isOk())
                .andReturn()
        def getByName = ResponseMapper.parseResponse(getByNameResponse).data.persons

        then: 'only resources with searched name'
        getByName.id == [id1, id2, id3, id4]
    }

    def 'DELETE: if resource not exists - 400'() {
        when:
        def responseOfDelete = mockMvcFacade.delete([url: "$root/1"])
                .andExpect(status().isBadRequest())
                .andReturn()

        def errors = ResponseMapper.parseResponse(responseOfDelete).data.errors

        then:
        errors.message == ['Person with id 1 not found.']
    }


    def 'DELETE: if resource exists - delete it and verify response'() {
        when: 'prepare resource to be further get'
        def createdPerson = personLifecycle.create([
                name   : 'X',
                surname: 'Y'
        ])

        and: 'delete previously created resource'
        def deletedPersonId = personLifecycle.deleteById(createdPerson.id)

        then: 'verify response of delete'
        deletedPersonId == createdPerson.id
    }

    def 'POST - create resource and verify response'() {
        when: 'create resource'
        def responseOfCreate = mockMvcFacade.post([
                url : root,
                body: [
                        name   : 'X',
                        surname: 'Y'
                ]
        ])
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("**$root/*"))
                .andReturn()
        def createdPerson = ResponseMapper.parseResponse(responseOfCreate).data.person

        then: 'verify response of create'
        createdPerson.id
        createdPerson.name == 'X'
        createdPerson.surname == 'Y'
        responseOfCreate.getResponse().getHeader('location').endsWith(createdPerson.id)
    }

    def 'POST &  GET: create resource and get it'() {
        when: 'prepare resource to be further get'
        def createdPerson = personLifecycle.create([
                name   : 'X',
                surname: 'Y'
        ])

        and: 'get previously created resource'
        def getPerson = personLifecycle.get(createdPerson.id)

        then: 'verify response of get'
        getPerson.id == createdPerson.id
        getPerson.name == 'X'
        getPerson.surname == 'Y'
    }

    def 'POST batch delete resources: positive scenario - all resources exist'() {
        given: 'prepare resources for further deletion'
        def id1 = personLifecycle.create([name: 'A', surname: 'A']).id
        def id2 = personLifecycle.create([name: 'B', surname: 'B']).id
        def id3 = personLifecycle.create([name: 'C', surname: 'C']).id
        def id4 = personLifecycle.create([name: 'D', surname: 'D']).id

        when: 'delete in batch'
        def deletedIds = personLifecycle.deleteByIds([id1, id2, id3, id4])

        then: 'verify answer'
        deletedIds == [id1, id2, id3, id4]

        and: 'using get verify that resources were successfully removed'
        personLifecycle.notExists(id1)
        personLifecycle.notExists(id2)
        personLifecycle.notExists(id3)
        personLifecycle.notExists(id4)
    }

    def 'POST batch delete resources: negative scenario - some resources do not exist'() {
        given: 'ids of resources that do not exist'
        def notExistingIds = ['1', '2', '3', '4']

        and: 'create resource that also be in the batch for removal'
        def notRemovedId = personLifecycle.create([name: 'A', surname: 'B']).id

        and: 'prepare ids for deletion'
        def ids = notExistingIds << notRemovedId

        when: 'delete in batch when some resources does not exist'
        def responseOfDelete = mockMvcFacade.post([
                url : "$root/delete",
                body: [ids: ids]
        ])
                .andExpect(status().isBadRequest())
                .andReturn()
        def errors = ResponseMapper.parseResponse(responseOfDelete).data.errors

        then: 'verify that resources that does not exist are specified in error messages'
        errors.message == ['Person with id 1 not found.',
                           'Person with id 2 not found.',
                           'Person with id 3 not found.',
                           'Person with id 4 not found.']

        and: 'verify that other resources were not removed'
        personLifecycle.get(notRemovedId)
    }
}
