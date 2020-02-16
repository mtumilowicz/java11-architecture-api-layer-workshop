package app.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class AppError {

    private ErrorCode code;
    private String message;

    @EqualsAndHashCode.Exclude
    private Throwable cause;

}
