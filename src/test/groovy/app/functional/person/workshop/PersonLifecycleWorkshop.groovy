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
        // facade post, [url:..., body:...]
        // verify that created, hint: andExpect, status(), isCreated()
        // parse response and get person, hint: ResponseMapper.parseResponse, data.person
    }

    def get(id) {
        // facade get, url
        // verify that status is 200, hint: andExpect, status(), isCreated()
        // parse response and get person, hint: ResponseMapper.parseResponse, data.person
    }

    def notExists(id) {
        // facade get, url
        // verify that status is not found, hint: andExpect, status(), isNotFound()
    }

    def deleteById(id) {
        // facade delete, url
        // verify that status is 200, hint: andExpect, status(), isCreated()
        // parse response and get personId, hint: ResponseMapper.parseResponse, data.personId
    }

    def deleteByIds(ids) {
        // facade post, url, ids
        // verify that status is 200, hint: andExpect, status(), isCreated()
        // parse response and get personIds, hint: ResponseMapper.parseResponse, data.personIds
    }

}
