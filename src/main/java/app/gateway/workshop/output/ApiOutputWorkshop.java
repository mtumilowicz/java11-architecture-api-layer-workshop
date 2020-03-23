package app.gateway.workshop.output;

import app.domain.results.Failures;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder(access = AccessLevel.PACKAGE)
@Getter
public class ApiOutputWorkshop {

    private String status;

    private Map<String, Object> data;

    static ApiOutputWorkshop fail(Failures failures) {
        // status fail
        // data: map of errors, failures asTuples
        return null;
    }

    static ApiOutputWorkshop success(String name, Object data) {
        // status success
        // data: map of name and data
        return null;
    }
}
