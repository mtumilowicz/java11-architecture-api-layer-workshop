package app.x;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class X {
    private String name;
}
