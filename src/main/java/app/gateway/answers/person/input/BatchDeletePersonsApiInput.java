package app.gateway.answers.person.input;

import app.domain.person.BatchDeletePersonsCommand;
import com.google.common.collect.ImmutableList;
import lombok.Getter;

import java.util.List;

@Getter
public class BatchDeletePersonsApiInput {
    List<String> ids;

    public BatchDeletePersonsCommand toDomain() {
        return BatchDeletePersonsCommand.builder()
                .ids(ImmutableList.copyOf(ids))
                .build();
    }
}
