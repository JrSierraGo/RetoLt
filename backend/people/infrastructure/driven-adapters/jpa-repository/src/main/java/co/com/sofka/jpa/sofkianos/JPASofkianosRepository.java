package co.com.sofka.jpa.sofkianos;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface JPASofkianosRepository extends ReactiveCrudRepository<SofkianoEntity, String>, ReactiveQueryByExampleExecutor<SofkianoEntity> {
}
