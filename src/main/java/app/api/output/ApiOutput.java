package app.api.output;

import app.domain.results.AppError;
import app.domain.results.UserError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ApiOutput {

    static ApiOutput error(List<AppError> appErrors) {
        List<Map<String, String>> apiErrors = appErrors.stream()
                .distinct()
                .map(AppError::asMap)
                .collect(Collectors.toList());

        return ErrorApiOutput.builder()
                .data(Map.of("errors", apiErrors))
                .build();
    }

    static ApiOutput fail(List<UserError> userErrors) {
        List<Map<String, String>> apiErrors = userErrors.stream()
                .distinct()
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
