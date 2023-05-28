package co.com.sofka.jpa.sofkianos;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.Sofkiano;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;


@Repository
public class JPASofkianosRepositoryAdapter extends AdapterOperations<Sofkiano, SofkianoEntity, String, JPASofkianosRepository> {

    protected JPASofkianosRepositoryAdapter(JPASofkianosRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Sofkiano.class));
    }
}
