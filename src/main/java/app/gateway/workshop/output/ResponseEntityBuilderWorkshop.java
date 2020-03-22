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
        return domainObject
                .map(item -> {
                    var body = ApiOutputWorkshop.success(name, mapper.apply(item));
                    return ResponseEntity.ok(body);
                }).getOrElse(ResponseEntity.notFound().build());
    }

    public <T> ResponseEntity<ApiOutputWorkshop> ok(Either<Failures, T> result, String name, Function<T, ?> mapper) {
        return result
                .map(item -> {
                    var body = ApiOutputWorkshop.success(name, mapper.apply(item));
                    return ResponseEntity.ok(body);
                }).getOrElseGet(ResponseEntityBuilderWorkshop::fromFailure);
    }

    public <T> ResponseEntity<ApiOutputWorkshop> created(Either<Failures, T> result,
                                                         String name,
                                                         Function<T, ?> mapper,
                                                         Function<T, URI> uriMapper) {
        return result.map(item -> {
                    var url = uriMapper.apply(item);
                    var body = ApiOutputWorkshop.success(name, mapper.apply(item));
                    return ResponseEntity.created(url)
                            .body(body);
                }
        ).getOrElseGet(ResponseEntityBuilderWorkshop::fromFailure);
    }

    public <T> ResponseEntity<ApiOutputWorkshop> okList(Either<Failures, List<T>> result,
                                                        String name,
                                                        Function<T, ?> mapper) {
        return result.map(items -> {
            var data = items.stream()
                    .map(mapper)
                    .collect(Collectors.toList());
            var body = ApiOutputWorkshop.success(name, data);
            return ResponseEntity.ok(body);
        }).getOrElseGet(ResponseEntityBuilderWorkshop::fromFailure);
    }

    private ResponseEntity<ApiOutputWorkshop> fromFailure(Failures failures) {
        return failures.map(userErrors -> {
                    ApiOutputWorkshop body = ApiOutputWorkshop.fail(userErrors);
                    return ResponseEntity.status(400).body(body);
                }
        );
    }
}
