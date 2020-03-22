package app.gateway.answers.output;

import app.domain.results.AppError;
import app.domain.results.UserError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ApiOutput {

    static ApiOutput error(List<AppError> appErrors) {
        var apiErrors = appErrors.stream()
                .map(AppError::asMap)
                .collect(Collectors.toList());

        return ErrorApiOutput.builder()
                .data(Map.of("errors", apiErrors))
                .build();
    }

    static ApiOutput fail(List<UserError> userErrors) {
        var apiErrors = userErrors.stream()
                .map(UserError::asMap)
                .collect(Collectors.toList());

        return FailApiOutput.builder()
                .data(Map.of("errors", apiErrors))
                .build();
    }

    static ApiOutput success(String name, Object data) {
        return SuccessApiOutput.builder()
                .data(Map.of(name, data))
                .build();
    }
}
