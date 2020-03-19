package app.api.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class SuccessApiOutput implements ApiOutput {

    private final String status = "success";

    private Map<String, Object> data;
}
