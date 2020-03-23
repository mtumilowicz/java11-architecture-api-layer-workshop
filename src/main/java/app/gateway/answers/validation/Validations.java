package app.gateway.answers.validation;

import app.domain.results.Failures;
import app.domain.results.Results;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class Validations {
    public <T> Validation<Failures, T> isNotNull(T obj, String name) {
        return Option.of(obj)
                .filter(Objects::nonNull)
                .map(Validation::<Failures, T>valid)
                .getOrElse(Validation.fromEither(Results.userError(name, "Cannot be null")));
    }
}
