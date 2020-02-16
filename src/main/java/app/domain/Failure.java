package app.domain;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import io.vavr.collection.Seq;
import lombok.*;

import java.util.List;
import java.util.function.Function;

@Builder(toBuilder = true, access = AccessLevel.PRIVATE)
@ToString
public class Failure {

    @Getter
    @Singular
    private List<AppError> appErrors;

    @Getter
    @Singular
    private List<UserError> userErrors;

    public boolean containsAll(List<UserError> userErrors) {
        return this.userErrors.containsAll(userErrors);
    }

    public Failure merge(Failure failure) {
        appErrors = Lists.newArrayList(Iterables.concat(appErrors, failure.appErrors));
        userErrors = Lists.newArrayList(Iterables.concat(userErrors, failure.userErrors));
        return this;
    }

    public <T> T transform(Function<List<AppError>, T> appErrorsMapper, Function<List<UserError>, T> userErrorsConsumer) {
        if (appErrors.isEmpty()) {
            return userErrorsConsumer.apply(userErrors);
        }

        return appErrorsMapper.apply(appErrors);
    }

    public static Failure merge(Seq<Failure> failures) {
        return failures.reduceLeft(Failure::merge);
    }

    public static Failure fromUserError(ErrorCode errorCode, String key, String message) {
        return Failure.builder()
                .userError(
                        UserError.builder()
                                .code(errorCode)
                                .key(key)
                                .message(message)
                                .build()
                )
                .build();
    }

    public static Failure fromAppError(ErrorCode errorCode, String message) {
        return fromAppError(errorCode, message, null);
    }

    public static Failure fromAppError(ErrorCode errorCode, String message, Throwable t) {
        return Failure.builder()
                .appError(
                        AppError.builder()
                                .code(errorCode)
                                .message(message)
                                .cause(t)
                                .build()
                )
                .build();
    }
}
