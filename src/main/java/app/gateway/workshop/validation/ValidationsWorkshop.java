package app.gateway.workshop.validation;

import app.domain.results.ErrorCode;
import app.domain.results.Failures;
import io.vavr.control.Option;
import io.vavr.control.Validation;

import java.util.Objects;

public class ValidationsWorkshop {
    public static <T> Validation<Failures, T> isNotNull(T obj, String name) {
        return Option.of(obj)
                .filter(Objects::nonNull)
                .map(Validation::<Failures, T>valid)
                .getOrElse(Validation.invalid(Failures.fromUserError(ErrorCode.VALIDATION_ERROR, name, "Cannot be null")));
    }
}
