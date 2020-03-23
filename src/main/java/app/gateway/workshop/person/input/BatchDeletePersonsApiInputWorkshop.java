package app.gateway.workshop.person.input;

import app.domain.person.BatchDeletePersonsCommand;
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
public class BatchDeletePersonsApiInputWorkshop {
    List<String> ids;

    public BatchDeletePersonsCommand toDomain() {
        return BatchDeletePersonsCommand.builder()
                .ids(ImmutableList.copyOf(ids))
                .build();
    }
}
