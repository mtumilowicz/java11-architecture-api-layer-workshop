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

    def 'GET: by name not exists'() {
        when:
        def getByNameResponse = mockMvcFacade.get([url: "$root?name=X"])
                .andExpect(status().isBadRequest())
                .andReturn()
        def errors = ResponseMapper.parseResponse(getByNameResponse).data.errors

        then:
        errors.message == ['There is no person with name X']
    }

    def 'GET: by name'() {
        given:
        def id1 = personLifecycle.create([name: 'X', surname: 'Y1']).id
        def id2 = personLifecycle.create([name: 'X', surname: 'Y2']).id
        def id3 = personLifecycle.create([name: 'X', surname: 'Y3']).id
        def id4 = personLifecycle.create([name: 'X', surname: 'Y4']).id
        personLifecycle.create([name: 'X1', surname: 'Y4'])
        personLifecycle.create([name: 'X2', surname: 'Y4'])
        personLifecycle.create([name: 'X3', surname: 'Y4'])

        when:
        def getByNameResponse = mockMvcFacade.get([url: "$root?name=X"])
                .andExpect(status().isOk())
                .andReturn()
        def getByName = ResponseMapper.parseResponse(getByNameResponse).data.persons

        then:
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

    def 'POST verify response'() {
        when: 'prepare resource to be further get'
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

    def 'DELETE: verify answer'() {
        when: 'prepare resource to be further get'
        def createdPerson = personLifecycle.create([
                name   : 'X',
                surname: 'Y'
        ])

        and: 'delete previously created resource'
        def deletedPersonId = personLifecycle.delete(createdPerson.id)

        then: 'verify response of get'
        deletedPersonId == createdPerson.id
    }
}
