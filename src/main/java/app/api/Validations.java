package app.api;

import app.domain.ErrorCode;
import app.domain.Failures;
import io.vavr.control.Option;
import io.vavr.control.Validation;

import java.util.Objects;

public class Validations {
    public static <T> Validation<Failures, T> isNotNull(T obj, String name) {
        return Option.of(obj)
                .filter(Objects::nonNull)
                .map(Validation::<Failures, T>valid)
                .getOrElse(Validation.invalid(Failures.fromUserError(ErrorCode.VALIDATION_ERROR, name, "Cannot be null")));
    }
}