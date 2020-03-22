package app.mockmvc.workshop

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.test.web.servlet.MvcResult

class ResponseMapperWorkshop {

    static ObjectMapper MAPPER = new ObjectMapper()

    static <T> T parseResponse(MvcResult result) {
        try {
            String contentAsString = result.getResponse().getContentAsString()
            return MAPPER.readValue(contentAsString, Object.class)
        } catch (IOException e) {
            throw new RuntimeException(e)
        }
    }
}
