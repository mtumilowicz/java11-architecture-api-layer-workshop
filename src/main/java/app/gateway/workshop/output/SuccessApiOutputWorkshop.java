package app.gateway.workshop.output;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder(access = AccessLevel.PACKAGE)
@Getter
class SuccessApiOutputWorkshop implements ApiOutputWorkshop {

    private final String status = "success";

    private Map<String, Object> data;
}
