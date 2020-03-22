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

    @Singular
    private List<UserError> userErrors;

    public Failures merge(Failures failures) {
        userErrors = Lists.newArrayList(Iterables.concat(userErrors, failures.userErrors));
        return this;
    }

    public <T> T map(Function<List<UserError>, T> mapper) {
        return mapper.apply(userErrors);
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
}
