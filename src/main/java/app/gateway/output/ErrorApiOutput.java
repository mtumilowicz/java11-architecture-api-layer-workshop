package app.gateway.output;

import lombok.*;

import java.util.Map;

@Builder(access = AccessLevel.PACKAGE)
@Getter
@NoArgsConstructor
@AllArgsConstructor
class ErrorApiOutput implements ApiOutput {

    private final String status = "error";

    private Map<String, Object> data;
}
