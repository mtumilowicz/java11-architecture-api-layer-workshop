package app.api.person.input;

import app.api.validation.Validations;
import app.domain.results.Failures;
import app.domain.person.NewPersonCommand;
import io.vavr.control.Either;
import io.vavr.control.Validation;

public class NewPersonApiInput {

    String name;
    String surname;

    public Either<Failures, NewPersonCommand> toDomain() {
        return Validation.combine(
                validateName(),
                validateSurname()
        )
                .ap((validName, validSurname) -> NewPersonCommand.builder()
                        .name(validName)
                        .surname(validSurname)
                        .build())
                .toEither()
                .mapLeft(Failures::merge);
    }

    private Validation<Failures, String> validateName() {
        return Validations.isNotNull(name, "name");
    }

    private Validation<Failures, String> validateSurname() {
        return Validations.isNotNull(surname, "surname");
    }
}
