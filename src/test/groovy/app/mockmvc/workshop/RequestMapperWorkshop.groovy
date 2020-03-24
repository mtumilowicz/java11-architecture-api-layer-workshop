package app.mockmvc.workshop


import com.fasterxml.jackson.databind.ObjectMapper

class RequestMapperWorkshop {

    static ObjectMapper MAPPER = new ObjectMapper()

    static String asJsonString(Object request) {
        // transform object to request, hint: MAPPER.writeValueAsString
    }

}
