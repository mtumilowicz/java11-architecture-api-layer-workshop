package app.gateway.output;

import lombok.*;

import java.util.Map;

@Builder(access = AccessLevel.PACKAGE)
@Getter
@NoArgsConstructor
@AllArgsConstructor
class SuccessApiOutput implements ApiOutput {

    private final String status = "success";

    private Map<String, Object> data;
}
