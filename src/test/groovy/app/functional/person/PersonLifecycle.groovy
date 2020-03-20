package app.functional.person

import app.gateway.output.ApiOutput
import app.gateway.output.SuccessApiOutput
import app.gateway.person.output.PersonApiOutput
import app.mockmvc.MockMvcFacade
import app.mockmvc.ResponseMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Component
class PersonLifecycle {

    @Autowired
    MockMvcFacade facade

    def root = '/persons'

    def create(person) {
        def responseOfCreate = facade.post([url: root, body: person])
                .andExpect(status().isCreated())
                .andReturn()
        ResponseMapper.parseResponse(responseOfCreate, SuccessApiOutput).data.person
    }

    def get(id) {
        def responseOfGet = facade.get([url: "$root/$id"])
                .andExpect(status().isOk())
                .andReturn()
        ResponseMapper.parseResponse(responseOfGet, SuccessApiOutput).data.person
    }

}
