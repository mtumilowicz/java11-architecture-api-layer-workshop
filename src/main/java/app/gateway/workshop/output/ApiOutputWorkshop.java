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
        return ApiOutputWorkshop.builder()
                .status("fail")
                .data(Map.of("errors", failures.asTuples()))
                .build();
    }

    static ApiOutputWorkshop success(String name, Object data) {
        return ApiOutputWorkshop.builder()
                .status("success")
                .data(Map.of(name, data))
                .build();
    }
}
