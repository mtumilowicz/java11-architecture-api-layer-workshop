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
//@AllArgsConstructor
public class BatchDeletePersonsApiInputWorkshop {

    // ids

    public BatchDeletePersonsCommand toDomain() {
        // ImmutableList
        return null;
    }
}
