package app.mockmvc.workshop


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@Component
class MockMvcFacadeWorkshop {

    @Autowired
    MockMvc mockMvc

    def post(Map request) {
        fireWithBody(MockMvcRequestBuilders.post(request.url), request)
    }

    def get(Map request) {
        fireWithoutBody(MockMvcRequestBuilders.get(request.url), request)
    }

    def delete(Map request) {
        fireWithoutBody(MockMvcRequestBuilders.delete(request.url), request)
    }

    private def fireWithoutBody(MockHttpServletRequestBuilder httpMethod, Map request) {
        // perform httpMethod, hint: mockMvc.perform
        // don't forget about headers (could be in request.headers), hint: httpMethod.headers, prepareHeaders
    }

    private def fireWithBody(MockHttpServletRequestBuilder httpMethod, Map request) {
        // perform httpMethod, hint: mockMvc.perform
        // don't forget about headers (could be in request.headers), hint: httpMethod.headers, prepareHeaders
        // content type: MediaType.APPLICATION_JSON, hint: contentType
        // content in request.body, hint: content, RequestMapperWorkshop.asJsonString
    }

    private def prepareHeaders(inputHeaders) {
        // hint: HttpHeaders
        // if inputHeaders not null - add each to HttpHeaders, hint: !inputHeaders, each, headerName, headerValue
        // return headers
    }
}
