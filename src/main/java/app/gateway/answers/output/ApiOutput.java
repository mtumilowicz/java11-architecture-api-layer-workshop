package app.gateway.answers.output;

import app.domain.results.Failures;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder(access = AccessLevel.PACKAGE)
@Getter
public class ApiOutput {

    private String status;

    private Map<String, Object> data;

    static ApiOutput fail(Failures failures) {
        return ApiOutput.builder()
                .status("fail")
                .data(Map.of("errors", failures.asTuples()))
                .build();
    }

    static ApiOutput success(String name, Object data) {
        return ApiOutput.builder()
                .status("success")
                .data(Map.of(name, data))
                .build();
    }
}
