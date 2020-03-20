package app.functional.person


import app.gateway.output.SuccessApiOutput
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
}
