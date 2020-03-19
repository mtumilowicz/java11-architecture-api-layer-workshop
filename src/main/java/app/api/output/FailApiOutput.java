package app.api.output;

import lombok.*;

import java.util.Map;

@Builder(access = AccessLevel.PACKAGE)
@Getter
@NoArgsConstructor
@AllArgsConstructor
class FailApiOutput implements ApiOutput {

    private final String status = "fail";

    private Map<String, Object> data;
}
