package app.domain.results;

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

    public Failures merge(Failures failures) {
        appErrors = Lists.newArrayList(Iterables.concat(appErrors, failures.appErrors));
        userErrors = Lists.newArrayList(Iterables.concat(userErrors, failures.userErrors));
        return this;
    }

    public <T> T transform(Function<List<AppError>, T> appErrorsMapper, Function<List<UserError>, T> userErrorsConsumer) {
        return appErrors.isEmpty()
                ? userErrorsConsumer.apply(userErrors)
                : appErrorsMapper.apply(appErrors);
    }

    public static Failures merge(Seq<Failures> failures) {
        return failures.reduceLeft(Failures::merge);
    }

    static Failures fromUserError(String key, String message) {
        return Failures.builder()
                .userError(
                        UserError.builder()
                                .key(key)
                                .message(message)
                                .build()
                )
                .build();
    }

    static Failures fromAppError(String message, Throwable t) {
        return Failures.builder()
                .appError(
                        AppError.builder()
                                .message(message)
                                .cause(t)
                                .build()
                )
                .build();
    }
}
