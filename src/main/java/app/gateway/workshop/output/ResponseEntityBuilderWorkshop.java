package app.gateway.workshop.output;

import app.domain.results.Failures;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class ResponseEntityBuilderWorkshop {

    public <T> ResponseEntity<ApiOutputWorkshop> okOrNotFound(Option<T> domainObject, String name, Function<T, ?> mapper) {
        // if not empty -> create success and then 200, hint: ApiOutputWorkshop.success, ResponseEntity.ok
        // if empty -> not found, hint: ResponseEntity.notFound()
        return null;
    }

    public <T> ResponseEntity<ApiOutputWorkshop> ok(Either<Failures, T> result, String name, Function<T, ?> mapper) {
        // if not empty -> create success and then 200, hint: ApiOutputWorkshop.success, ResponseEntity.ok
        // if empty -> 400 and failures as a body, hint: fromFailures
        return null;
    }

    public <T> ResponseEntity<ApiOutputWorkshop> created(Either<Failures, T> result,
                                                         String name,
                                                         Function<T, ?> mapper,
                                                         Function<T, URI> uriMapper) {
        // if is not empty
        // create url, hint: uriMapper
        // transform item and wrap it into success, hint: mapper, ApiOutputWorkshop.success
        // return 201, hint: ResponseEntity.created
        // if empty 400, hint: fromFailures
        return null;
    }

    public <T> ResponseEntity<ApiOutputWorkshop> okList(Either<Failures, List<T>> result,
                                                        String name,
                                                        Function<T, ?> mapper) {
        // if not empty
        // transform every item with mapper
        // wrap in success
        // return 200
        // if empty
        return null;
    }

    private ResponseEntity<ApiOutputWorkshop> fromFailures(Failures failures) {
        // create fail, hint: ApiOutputWorkshop.fail
        // return 400, hint: ResponseEntity.badRequest()
        return null;
    }
}
