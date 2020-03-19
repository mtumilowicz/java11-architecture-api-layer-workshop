package app.api;

import app.domain.ErrorCode;
import app.domain.Failure;
import io.vavr.control.Option;
import io.vavr.control.Validation;

import java.util.Objects;

public class Validations {
    public static <T> Validation<Failure, T> isNotNull(T obj, String name) {
        return Option.of(obj)
                .filter(Objects::nonNull)
                .map(Validation::<Failure, T>valid)
                .getOrElse(Validation.invalid(Failure.fromUserError(ErrorCode.VALIDATION_ERROR, name, "Cannot be null")));
    }
}
