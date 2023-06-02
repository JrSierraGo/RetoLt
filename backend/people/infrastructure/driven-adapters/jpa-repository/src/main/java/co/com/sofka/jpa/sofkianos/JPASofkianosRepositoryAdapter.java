package co.com.sofka.jpa.sofkianos;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public class JPASofkianosRepositoryAdapter extends AdapterOperations<Sofkiano, SofkianoEntity, String, JPASofkianosRepository> implements SofkianoRepository {

    protected JPASofkianosRepositoryAdapter(JPASofkianosRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Sofkiano.class));
    }
}
