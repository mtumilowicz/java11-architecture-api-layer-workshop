package app.domain.results;

import io.vavr.control.Either;

import java.util.List;

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
}
