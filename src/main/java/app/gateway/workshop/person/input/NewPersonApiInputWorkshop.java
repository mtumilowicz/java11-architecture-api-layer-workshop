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
//@AllArgsConstructor
public class NewPersonApiInputWorkshop {

    // name, surname

    public Either<Failures, NewPersonCommand> toDomain() {
        // validate name, validate surname, hint: Validation.combine, validateName(), validateSurname()
        // create NewPersonCommand, hint: ap
        // to either and merge failures, hint: toEither, Failures::merge
        return null;
    }

    private Validation<Failures, String> validateName() {
        // validate if not null, hint: ValidationsWorkshop.isNotNull
        return null;
    }

    private Validation<Failures, String> validateSurname() {
        // validate if not null, hint: ValidationsWorkshop.isNotNull
        return null;
    }
}
