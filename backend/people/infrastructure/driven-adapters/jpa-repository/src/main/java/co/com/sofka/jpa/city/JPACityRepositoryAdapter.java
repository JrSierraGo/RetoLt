package co.com.sofka.jpa.city;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.city.City;
import co.com.sofka.model.city.gateways.CityRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class JPACityRepositoryAdapter extends AdapterOperations<CityEntity, City, String, JPACityRepository> implements CityRepository {


    protected JPACityRepositoryAdapter(JPACityRepository repository, ObjectMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Flux<City> findByStateId(String stateId) {
        return Flux.fromIterable(repository.findByStateId(stateId))
                .map(this::toData);
    }
}
