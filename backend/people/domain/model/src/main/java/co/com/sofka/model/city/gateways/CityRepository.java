package co.com.sofka.model.city.gateways;

import co.com.sofka.model.city.City;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CityRepository {

    Flux<City> findByStateId(String stateId);

    Mono<City> findById(String id);

}
