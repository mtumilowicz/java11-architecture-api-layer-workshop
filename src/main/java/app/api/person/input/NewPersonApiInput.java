package app.api.person.input;

import app.api.Validations;
import app.domain.Failure;
import app.domain.person.NewPersonCommand;
import io.vavr.control.Either;
import io.vavr.control.Validation;

public class NewPersonApiInput {

    String name;
    String surname;

    public Either<Failure, NewPersonCommand> toDomain() {
        return Validation.combine(
                validateName(),
                validateSurname()
        )
                .ap((validName, validSurname) -> NewPersonCommand.builder()
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
