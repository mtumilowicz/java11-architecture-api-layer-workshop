package app.gateway.transactional;

import app.domain.results.Failures;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Supplier;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Transaction {

    TransactionTemplate transactionTemplate;

    public <T> Either<Failures, T> execute(Supplier<Either<Failures, T>> action) {
        return transactionTemplate.execute(status ->
                action.get().peekLeft(ignore -> status.setRollbackOnly())
        );
    }
}