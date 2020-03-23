package app.gateway.workshop.validation;

import app.domain.results.Failures;
import io.vavr.control.Validation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationsWorkshop {
    public <T> Validation<Failures, T> isNotNull(T obj, String name) {
        // if null -> failure, hint: Results.userError
        // otherwise valid, hint: Validation::<Failures, T>valid
        return null;
    }
}
