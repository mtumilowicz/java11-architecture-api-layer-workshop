package app.api.person.input;

import app.domain.ErrorCode;
import app.domain.Failure;
import app.domain.person.PersonCreationInput;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Validation;

import java.util.Objects;

public class PersonCreationApiInput {

    String name;
    String surname;

    public Either<Failure, PersonCreationInput> toDomain() {
        return Validation.combine(
                isNotNull(name, "name"),
                isNotNull(surname, "surname")
        )
                .ap((validName, validSurname) -> PersonCreationInput.builder()
                        .name(validName)
                        .build())
                .toEither()
                .mapLeft(Failure::merge);
    }

    static <T> Validation<Failure, T> isNotNull(T obj, String name) {
        return Option.of(obj)
                .filter(Objects::nonNull)
                .map(Validation::<Failure, T>valid)
                .getOrElse(Validation.invalid(Failure.fromUserError(ErrorCode.VALIDATION_ERROR, name, "Cannot be null")));
    }
}
