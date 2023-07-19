package co.com.sofka.jpa.location;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.location.Location;
import co.com.sofka.model.location.gateways.LocationRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class JPALocationRepositoryAdapter extends AdapterOperations<LocationEntity, Location, String, JPALocationRepository> implements LocationRepository {

    protected JPALocationRepositoryAdapter(JPALocationRepository repository, ObjectMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Mono<Location> findByStatusAndIdNot(String status, String id) {
        return Mono.justOrEmpty(repository.findByStatusAndIdNot(status, id))
                .map(this::toData);
    }

    @Override
    public Mono<Location> findBySofkianoId(String sofkianoId) {
        return Mono.justOrEmpty(repository.findBySofkianoId(sofkianoId))
                .map(this::toData);
    }
}
