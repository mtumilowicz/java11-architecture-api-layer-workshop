package app.domain.results;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Builder
@EqualsAndHashCode
@ToString
@Getter
public class AppError {

    private ErrorCode code;
    private String message;

    @EqualsAndHashCode.Exclude
    private Throwable cause;

    public Map<String, String> asMap() {
        return Map.of("message", message);
    }
}
