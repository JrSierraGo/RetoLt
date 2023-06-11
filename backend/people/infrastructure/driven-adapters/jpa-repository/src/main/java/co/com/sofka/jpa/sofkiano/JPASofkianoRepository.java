package co.com.sofka.jpa.sofkiano;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;


public interface JPASofkianoRepository extends CrudRepository<SofkianoEntity, String>, QueryByExampleExecutor<SofkianoEntity> {
}
