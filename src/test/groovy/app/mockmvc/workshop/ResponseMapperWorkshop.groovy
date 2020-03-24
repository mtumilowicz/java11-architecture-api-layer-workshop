package app.mockmvc.workshop

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.test.web.servlet.MvcResult

class ResponseMapperWorkshop {

    static ObjectMapper MAPPER = new ObjectMapper()

    static def parseResponse(MvcResult result) {
        // get content as string, hint: result.getResponse().getContentAsString()
        // map to object, MAPPER.readValue, Object.class
    }
}
