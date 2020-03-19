package app.api.output;

import app.domain.results.Failures;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResponseEntityBuilder {

    public static <T> ResponseEntity<ApiOutput> okOrNotFound(Option<T> domainObject, String name, Function<T, ?> mapper) {
        return domainObject
                .map(item -> {
                    var body = ApiOutput.success(name, mapper.apply(item));
                    return ResponseEntity.ok(body);
                })
                .getOrElse(ResponseEntity.notFound().build());
    }

    public static <T> ResponseEntity<ApiOutput> created200(Either<Failures, T> result,
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

    public static <T> ResponseEntity<ApiOutput> list200(Either<Failures, List<T>> result,
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

    private static ResponseEntity<ApiOutput> fromFailure(Failures failures) {
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
