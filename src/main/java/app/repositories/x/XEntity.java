package app.repositories.x;

import app.x.X;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class XEntity {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String name;

    public static XEntity from(X x) {
        return XEntity.builder()
                .name(x.getName())
                .build();
    }

    public X rebuild(X x) {
        return x.toBuilder()
                .build();
    }
}
