package app.api.person.input;

import app.api.Validations;
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
                validateName(),
                validateSurname()
        )
                .ap((validName, validSurname) -> PersonCreationInput.builder()
                        .name(validName)
                        .surname(validSurname)
                        .build())
                .toEither()
                .mapLeft(Failure::merge);
    }

    private Validation<Failure, String> validateName() {
        return Validations.isNotNull(name, "name");
    }

    private Validation<Failure, String> validateSurname() {
        return Validations.isNotNull(surname, "surname");
    }
}
