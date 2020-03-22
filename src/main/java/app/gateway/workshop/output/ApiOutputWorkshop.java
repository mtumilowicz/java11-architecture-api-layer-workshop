package app.gateway.workshop.output;

import app.domain.results.AppError;
import app.domain.results.UserError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ApiOutputWorkshop {

    static ApiOutputWorkshop error(List<AppError> appErrors) {
        List<Map<String, String>> apiErrors = appErrors.stream()
                .distinct()
                .map(AppError::asMap)
                .collect(Collectors.toList());

        return ErrorApiOutputWorkshop.builder()
                .data(Map.of("errors", apiErrors))
                .build();
    }

    static ApiOutputWorkshop fail(List<UserError> userErrors) {
        List<Map<String, String>> apiErrors = userErrors.stream()
                .distinct()
                .map(UserError::asMap)
                .collect(Collectors.toList());

        return FailApiOutputWorkshop.builder()
                .data(Map.of("errors", apiErrors))
                .build();
    }

    static ApiOutputWorkshop success(String name, Object data) {
        return SuccessApiOutputWorkshop.builder()
                .data(Map.of(name, data))
                .build();
    }
}
