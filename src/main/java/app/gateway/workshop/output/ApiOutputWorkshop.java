package app.gateway.workshop.output;

import app.domain.results.Failure;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder(access = AccessLevel.PACKAGE)
@Getter
public class ApiOutputWorkshop {

    private String status;

    private Map<String, Object> data;

    static ApiOutputWorkshop fail(List<Failure> failures) {
        var apiErrors = failures.stream()
                .map(Failure::asMap)
                .collect(Collectors.toList());

        return ApiOutputWorkshop.builder()
                .status("fail")
                .data(Map.of("errors", apiErrors))
                .build();
    }

    static ApiOutputWorkshop success(String name, Object data) {
        return ApiOutputWorkshop.builder()
                .status("success")
                .data(Map.of(name, data))
                .build();
    }
}
