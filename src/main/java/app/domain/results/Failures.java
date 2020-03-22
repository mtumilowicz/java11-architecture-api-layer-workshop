package app.domain.results;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Seq;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Singular;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Builder(access = AccessLevel.PRIVATE)
@ToString
public class Failures {

    @Singular
    private ImmutableList<Failure> failures;

    public ImmutableList<ImmutableMap<String, String>> asTuples() {
        return failures.stream()
                .map(Failure::toTuple)
                .collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
    }

    public Failures merge(Failures failures) {
        this.failures = ImmutableList.copyOf(Iterables.concat(this.failures, failures.failures));
        return this;
    }

    public <T> T map(Function<List<Failure>, T> mapper) {
        return mapper.apply(failures);
    }

    public static Failures merge(Seq<Failures> failures) {
        return failures.reduceLeft(Failures::merge);
    }

    static Failures fromUserError(String key, String message) {
        return Failures.builder()
                .failure(Failure.of(key, message))
                .build();
    }
}
