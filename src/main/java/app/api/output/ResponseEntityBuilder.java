package app.api.output;

import app.domain.results.Failures;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResponseEntityBuilder {

    public static <T> ResponseEntity<ApiOutput> okOrNotFound(Option<T> domainObject, String name, Function<T, ?> mapper) {
        return domainObject
                .map(item -> ResponseEntity.ok(SuccessApiOutput.from(name, mapper.apply(item))))
                .getOrElse(ResponseEntity.notFound().build());
    }

    public static <T> ResponseEntity<ApiOutput> created200(Either<Failures, T> result,
                                                           String name,
                                                           Function<T, ?> mapper,
                                                           Function<T, URI> uriMapper) {
        return result.map(item ->
                ResponseEntity.created(uriMapper.apply(item)).body(SuccessApiOutput.from(name, mapper.apply(item)))
        ).getOrElseGet(ResponseEntityBuilder::fromFailure);
    }

    public static <T> ResponseEntity<ApiOutput> list200(Either<Failures, List<T>> result,
                                                        String name,
                                                        Function<T, ?> mapper) {
        return result.map(items -> {
            List<?> apiItems = items.stream()
                    .map(mapper)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(SuccessApiOutput.from(name, apiItems));
        }).getOrElseGet(ResponseEntityBuilder::fromFailure);
    }

    private static ResponseEntity<ApiOutput> fromFailure(Failures failures) {
        return failures.transform(
                appErrors -> {
                    ApiOutput body = ErrorApiOutput.fromAppErrors(appErrors);
                    return ResponseEntity.status(500).body(body);
                }, userErrors -> {
                    ApiOutput body = FailApiOutput.fromUserErrors(userErrors);
                    return ResponseEntity.status(400).body(body);
                }
        );
    }
}
