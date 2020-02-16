package app.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessApiOutput implements ApiOutput {
    private final String status = "success";
    private Map<String, Object> data;
}
