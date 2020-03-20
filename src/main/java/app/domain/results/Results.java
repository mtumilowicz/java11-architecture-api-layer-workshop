package app.domain.results;

import io.vavr.Value;
import io.vavr.control.Either;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.function.Predicate.not;

public class Results {

    public static <T> Either<Failures, T> success(T value) {
        return Either.right(value);
    }

    public static <T> Either<Failures, T> userError(ErrorCode errorCode, String message) {
        return Either.left(Failures.fromUserError(errorCode, "message", message));
    }

    public static <T> Either<Failures, T> appError(ErrorCode errorCode, String message, Throwable t) {
        return Either.left(Failures.fromAppError(errorCode, message, t));
    }

    public static <T> Either<Failures, List<T>> requireNotEmpty(Either<Failures, List<T>> list, String message) {
        return list.filter(not(List::isEmpty))
                .getOrElse(Results.userError(ErrorCode.VALIDATION_ERROR, message));
    }

    public static  <T> Either<Failures, List<T>> reduce(Either<Failures, List<T>> first, Either<Failures, T> second) {
        return merge(first, second.map(List::of));
    }

    public static <T> Either<Failures, List<T>> merge(Either<Failures, List<T>> first, Either<Failures, List<T>> second) {
        return Either.sequence(Arrays.asList(first, second))
                .map(x -> x.flatMap(Function.identity()))
                .map(Value::toJavaList)
                .mapLeft(failures -> failures.reduce(Failures::merge));
    }
}
