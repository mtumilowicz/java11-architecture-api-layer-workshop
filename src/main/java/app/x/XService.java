package app.x;

import app.domain.Failure;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class XService {
    private XRepository xRepository;

    public Either<Failure, X> save(X x) {
        return xRepository.save(x);
    }
}
