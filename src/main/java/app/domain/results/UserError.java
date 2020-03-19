package app.domain.results;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class UserError {

    private ErrorCode code;
    private String key;
    private String message;
}
