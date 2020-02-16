package app.domain;

import io.vavr.control.Either;

public class Results {
    public static Either<Failure, Success> success() {
        return Either.right(new Success());
    }
    public static <T> Either<Failure, T> success(T value) {
        return Either.right(value);
    }
    public static Either<Failure, Success> failure(Failure failure) {
        return Either.left(failure);
    }

    public static <T> Either<Failure, T> userError(ErrorCode errorCode, String key, String message) {
        return Either.left(Failure.fromUserError(errorCode, key, message));
    }

    public static <T> Either<Failure, T> userError(ErrorCode errorCode, String message) {
        return Either.left(Failure.fromUserError(errorCode, "message", message));
    }

    public static <T> Either<Failure, T> appError(ErrorCode errorCode, String message) {
        return Either.left(Failure.fromAppError(errorCode, message));
    }

    public static <T> Either<Failure, T> appError(ErrorCode errorCode, String message, Throwable t) {
        return Either.left(Failure.fromAppError(errorCode, message, t));
    }

}
