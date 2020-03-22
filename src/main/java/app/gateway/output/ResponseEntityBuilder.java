package app.gateway.output;

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
public class ResponseEntityBuilder {

    public <T> ResponseEntity<ApiOutput> okOrNotFound(Option<T> domainObject, String name, Function<T, ?> mapper) {
        return domainObject
                .map(item -> {
                    var body = ApiOutput.success(name, mapper.apply(item));
                    return ResponseEntity.ok(body);
                }).getOrElse(ResponseEntity.notFound().build());
    }

    public <T> ResponseEntity<ApiOutput> ok(Either<Failures, T> result, String name, Function<T, ?> mapper) {
        return result
                .map(item -> {
                    var body = ApiOutput.success(name, mapper.apply(item));
                    return ResponseEntity.ok(body);
                }).getOrElseGet(ResponseEntityBuilder::fromFailure);
    }

    public <T> ResponseEntity<ApiOutput> created(Either<Failures, T> result,
                                                        String name,
                                                        Function<T, ?> mapper,
                                                        Function<T, URI> uriMapper) {
        return result.map(item -> {
                    var url = uriMapper.apply(item);
                    var body = ApiOutput.success(name, mapper.apply(item));
                    return ResponseEntity.created(url)
                            .body(body);
                }
        ).getOrElseGet(ResponseEntityBuilder::fromFailure);
    }

    public <T> ResponseEntity<ApiOutput> okList(Either<Failures, List<T>> result,
                                                       String name,
                                                       Function<T, ?> mapper) {
        return result.map(items -> {
            var data = items.stream()
                    .map(mapper)
                    .collect(Collectors.toList());
            var body = ApiOutput.success(name, data);
            return ResponseEntity.ok(body);
        }).getOrElseGet(ResponseEntityBuilder::fromFailure);
    }

    private ResponseEntity<ApiOutput> fromFailure(Failures failures) {
        return failures.transform(
                appErrors -> {
                    ApiOutput body = ApiOutput.error(appErrors);
                    return ResponseEntity.status(500).body(body);
                }, userErrors -> {
                    ApiOutput body = ApiOutput.fail(userErrors);
                    return ResponseEntity.status(400).body(body);
                }
        );
    }
}
