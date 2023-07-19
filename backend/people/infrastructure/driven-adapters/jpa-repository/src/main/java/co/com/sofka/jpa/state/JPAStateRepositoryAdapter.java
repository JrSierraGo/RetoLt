package co.com.sofka.jpa.state;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.state.State;
import co.com.sofka.model.state.gateways.StateRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class JPAStateRepositoryAdapter extends AdapterOperations<StateEntity, State, String, JPAStateRepository> implements StateRepository {


    protected JPAStateRepositoryAdapter(JPAStateRepository repository, ObjectMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Flux<State> findByCountryId(String countryId) {
        return Flux.fromIterable(repository.findByCountryId(countryId))
                .map(this::toData);
    }
}
