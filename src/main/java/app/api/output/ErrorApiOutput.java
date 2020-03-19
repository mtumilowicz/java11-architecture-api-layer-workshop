package app.api.output;

import app.domain.results.AppError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
class ErrorApiOutput implements ApiOutput {

    private final String status = "error";

    private Map<String, Object> data;

    static ErrorApiOutput fromAppErrors(List<AppError> appErrors) {
        List<Map<String, String>> apiErrors = appErrors.stream()
                .distinct()
                .map(AppError::asMap)
                .collect(Collectors.toList());

        return ErrorApiOutput.builder()
                .data(Map.of("errors", apiErrors))
                .build();
    }
}
