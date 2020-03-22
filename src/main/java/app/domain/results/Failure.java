package app.domain.results;

import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class Failure {

    String key;

    String message;

    public ImmutableMap<String, String> toTuple() {
        return ImmutableMap.of(key, message);
    }

    public static Failure of(String key, String message) {
        return new Failure(key, message);
    }
}
