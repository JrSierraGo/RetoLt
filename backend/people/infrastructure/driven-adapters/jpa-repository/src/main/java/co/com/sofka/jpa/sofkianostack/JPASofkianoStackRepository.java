package co.com.sofka.jpa.sofkianostack;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPASofkianoStackRepository extends CrudRepository<SofkianoStackEntity, String>, QueryByExampleExecutor<SofkianoStackEntity> {
}
