package app.gateway.person.input;

import app.gateway.validation.Validations;
import app.domain.results.Failures;
import app.domain.person.NewPersonCommand;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
