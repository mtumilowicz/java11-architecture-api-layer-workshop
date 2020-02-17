package app.api;

import app.domain.Failure;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResponseEntityBuilder {

    public static <T> ResponseEntity<ApiOutput> created200(Either<Failure, T> result,
                                                           String name,
                                                           Function<T, ?> mapper,
                                                           Function<T, URI> uriMapper) {
        return result.map(item -> {
            Object apiItem = mapper.apply(item);
            ApiOutput body = SuccessApiOutput.builder()
                    .data(Map.of(name, apiItem))
                    .build();
            return ResponseEntity.created(uriMapper.apply(item)).body(body);
        }).getOrElseGet(ResponseEntityBuilder::fromFailure);
    }

    public static <T, R> ResponseEntity<ApiOutput> list200(Either<Failure, List<T>> result,
                                                           String name,
                                                           Function<T, R> mapper) {
        return result.map(items -> {
            List<R> apiItems = items.stream()
                    .map(mapper)
                    .collect(Collectors.toList());
            ApiOutput body = SuccessApiOutput.builder()
                    .data(Map.of(name, apiItems))
                    .build();
            return ResponseEntity.ok(body);
        }).getOrElseGet(ResponseEntityBuilder::fromFailure);
    }

    private static ResponseEntity<ApiOutput> fromFailure(Failure failure) {
        return failure.transform(
                appErrors -> {
                    ApiOutput body = ErrorApiOutput.fromAppErrors(appErrors);
                    return ResponseEntity.status(500).body(body);
                }, userErrors -> {
                    ApiOutput body = FailApiOutput.fromUserErrors(userErrors);
                    return ResponseEntity.status(500).body(body);
                }
        );
    }
}
