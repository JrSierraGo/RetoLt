package co.com.sofka.jpa.sofkianos;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;


@Repository
public class JPASofkianoRepositoryAdapter extends AdapterOperations<SofkianoEntity, Sofkiano, String, JPASofkianoRepository> implements SofkianoRepository {

    protected JPASofkianoRepositoryAdapter(JPASofkianoRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, SofkianoEntity.class));
    }
}
