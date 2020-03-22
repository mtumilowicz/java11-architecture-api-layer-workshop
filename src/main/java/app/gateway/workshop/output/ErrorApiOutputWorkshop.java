package app.gateway.workshop.output;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder(access = AccessLevel.PACKAGE)
@Getter
class ErrorApiOutputWorkshop implements ApiOutputWorkshop {

    private final String status = "error";

    private Map<String, Object> data;
}
