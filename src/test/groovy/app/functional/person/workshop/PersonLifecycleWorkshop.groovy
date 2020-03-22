package app.functional.person.workshop


import app.mockmvc.answers.MockMvcFacade
import app.mockmvc.answers.ResponseMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Component
class PersonLifecycleWorkshop {

    @Autowired
    MockMvcFacade facade

    def root = '/workshop/persons'

    def create(person) {
        def responseOfCreate = facade.post([url: root, body: person])
                .andExpect(status().isCreated())
                .andReturn()
        ResponseMapper.parseResponse(responseOfCreate).data.person
    }

    def get(id) {
        def responseOfGet = facade.get([url: "$root/$id"])
                .andExpect(status().isOk())
                .andReturn()
        ResponseMapper.parseResponse(responseOfGet).data.person
    }

    def notExists(id) {
        facade.get([url: "$root/$id"])
                .andExpect(status().isNotFound())
    }

    def deleteById(id) {
        def responseOfGet = facade.delete([url: "$root/$id"])
                .andExpect(status().isOk())
                .andReturn()
        ResponseMapper.parseResponse(responseOfGet).data.personId
    }

    def deleteByIds(ids) {
        def responseOfDelete = facade.post([url: "$root/delete", body: [ids: ids]])
                .andExpect(status().isOk())
                .andReturn()
        ResponseMapper.parseResponse(responseOfDelete).data.personIds
    }

}
