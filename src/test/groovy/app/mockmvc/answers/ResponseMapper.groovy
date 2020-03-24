package app.mockmvc.answers

import app.gateway.answers.output.ApiOutput
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.test.web.servlet.MvcResult

class ResponseMapper {

    static ObjectMapper MAPPER = new ObjectMapper()

    static ApiOutput parseResponse(MvcResult result) {
        try {
            String contentAsString = result.getResponse().getContentAsString()
            return MAPPER.readValue(contentAsString, ApiOutput.class)
        } catch (IOException e) {
            throw new RuntimeException(e)
        }
    }
}
