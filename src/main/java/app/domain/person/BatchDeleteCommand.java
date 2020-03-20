package app.domain.person;

import com.google.common.collect.ImmutableList;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.stream.Stream;

@Builder
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BatchDeleteCommand {
    ImmutableList<String> ids;

    public Stream<String> ids() {
        return ids.stream();
    }
}
