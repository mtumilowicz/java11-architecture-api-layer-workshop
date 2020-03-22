package app.gateway.answers.output;

import app.domain.results.AppError;
import app.domain.results.UserError;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder(access = AccessLevel.PACKAGE)
@Getter
public class ApiOutput {

    private String status;

    private Map<String, Object> data;

    static ApiOutput error(List<AppError> appErrors) {
        var apiErrors = appErrors.stream()
                .map(AppError::asMap)
                .collect(Collectors.toList());

        return ApiOutput.builder()
                .status("error")
                .data(Map.of("errors", apiErrors))
                .build();
    }

    static ApiOutput fail(List<UserError> userErrors) {
        var apiErrors = userErrors.stream()
                .map(UserError::asMap)
                .collect(Collectors.toList());

        return ApiOutput.builder()
                .status("fail")
                .data(Map.of("errors", apiErrors))
                .build();
    }

    static ApiOutput success(String name, Object data) {
        return ApiOutput.builder()
                .status("success")
                .data(Map.of(name, data))
                .build();
    }
}
