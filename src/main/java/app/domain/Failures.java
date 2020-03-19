package app.domain;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import io.vavr.collection.Seq;
import lombok.*;

import java.util.List;
import java.util.function.Function;

@Builder(toBuilder = true, access = AccessLevel.PRIVATE)
@ToString
public class Failures {

    @Getter
    @Singular
    private List<AppError> appErrors;

    @Getter
    @Singular
    private List<UserError> userErrors;

    public boolean containsAll(List<UserError> userErrors) {
        return this.userErrors.containsAll(userErrors);
    }

    public Failures merge(Failures failures) {
        appErrors = Lists.newArrayList(Iterables.concat(appErrors, failures.appErrors));
        userErrors = Lists.newArrayList(Iterables.concat(userErrors, failures.userErrors));
        return this;
    }

    public <T> T transform(Function<List<AppError>, T> appErrorsMapper, Function<List<UserError>, T> userErrorsConsumer) {
        if (appErrors.isEmpty()) {
            return userErrorsConsumer.apply(userErrors);
        }

        return appErrorsMapper.apply(appErrors);
    }

    public static Failures merge(Seq<Failures> failures) {
        return failures.reduceLeft(Failures::merge);
    }

    public static Failures fromUserError(ErrorCode errorCode, String key, String message) {
        return Failures.builder()
                .userError(
                        UserError.builder()
                                .code(errorCode)
                                .key(key)
                                .message(message)
                                .build()
                )
                .build();
    }

    public static Failures fromAppError(ErrorCode errorCode, String message) {
        return fromAppError(errorCode, message, null);
    }

    public static Failures fromAppError(ErrorCode errorCode, String message, Throwable t) {
        return Failures.builder()
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
