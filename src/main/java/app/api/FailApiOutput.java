package app.api;

import app.domain.UserError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FailApiOutput implements ApiOutput {
    private final String status = "fail";
    private Map<String, Object> data;

    public static ApiOutput fromUserErrors(List<UserError> userErrors) {
        List<Map<String, String>> apiErrors = userErrors.stream()
                .distinct()
                .map(userError -> Map.of(userError.getKey(), userError.getMessage()))
                .collect(Collectors.toList());

        return ErrorApiOutput.builder()
                .data(Map.of("errors", apiErrors))
                .build();
    }

    public static ApiOutput fromUserError(UserError userError) {
        return fromUserErrors(Collections.singletonList(userError));
    }
}
