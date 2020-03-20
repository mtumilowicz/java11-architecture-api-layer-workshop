package app.gateway.person.input;

import app.domain.person.BatchDeleteCommand;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchDeleteApiInput {
    List<String> ids;

    public BatchDeleteCommand toDomain() {
        return BatchDeleteCommand.builder()
                .ids(ImmutableList.copyOf(ids))
                .build();
    }
}
