package co.com.sofka.jpa.sofkianostack;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.sofkianostack.Stack;
import co.com.sofka.model.sofkianostack.gateways.SofkianoStackRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JPASofkianoStackRepositoryAdapter extends AdapterOperations<SofkianoStackEntity, Stack, String, JPASofkianoStackRepository> implements SofkianoStackRepository {
    protected JPASofkianoStackRepositoryAdapter(JPASofkianoStackRepository repository, ObjectMapper mapper) {
        super(repository, mapper);
    }
}
