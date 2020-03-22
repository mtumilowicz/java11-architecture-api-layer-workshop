package app.gateway.workshop.person.input;

import app.domain.person.NewPersonCommand;
import app.domain.results.Failures;
import app.gateway.workshop.validation.ValidationsWorkshop;
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
public class NewPersonApiInputWorkshop {

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
        return ValidationsWorkshop.isNotNull(name, "name");
    }

    private Validation<Failures, String> validateSurname() {
        return ValidationsWorkshop.isNotNull(surname, "surname");
    }
}
