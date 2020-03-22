package app.gateway.answers.output;

import lombok.*;

import java.util.Map;

@Builder(access = AccessLevel.PACKAGE)
@Getter
class ErrorApiOutput implements ApiOutput {

    private final String status = "error";

    private Map<String, Object> data;
}
