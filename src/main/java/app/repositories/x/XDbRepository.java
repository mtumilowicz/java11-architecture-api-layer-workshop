package app.repositories.x;

import app.domain.ErrorCode;
import app.domain.Failure;
import app.domain.Results;
import app.x.X;
import app.x.XRepository;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class XDbRepository implements XRepository {

    @Autowired
    private XJpaRepository jpaRepository;


    @Override
    public Either<Failure, X> save(X x) {
        try {
            XEntity entity = jpaRepository.save(XEntity.from(x));
            return Results.success(entity.rebuild(x));
        } catch (RuntimeException ex) {
            return Results.appError(ErrorCode.SERVER_ERROR, "Database error.", ex);
        }

    }
}
