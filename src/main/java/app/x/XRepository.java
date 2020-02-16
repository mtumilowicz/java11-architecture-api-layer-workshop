package app.x;

import app.domain.Failure;
import io.vavr.control.Either;

public interface XRepository {
    Either<Failure, X> save(X x);
}
