package app.api.output;

import app.domain.results.UserError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
class FailApiOutput implements ApiOutput {

    private final String status = "fail";

    private Map<String, Object> data;

    static ApiOutput fromUserErrors(List<UserError> userErrors) {
        List<Map<String, String>> apiErrors = userErrors.stream()
                .distinct()
                .map(UserError::asMap)
                .collect(Collectors.toList());

        return ErrorApiOutput.builder()
                .data(Map.of("errors", apiErrors))
                .build();
    }

    static ApiOutput fromUserError(UserError userError) {
        return fromUserErrors(Collections.singletonList(userError));
    }
}
