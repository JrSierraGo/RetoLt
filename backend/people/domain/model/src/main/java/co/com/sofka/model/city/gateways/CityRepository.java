package co.com.sofka.model.city.gateways;

import co.com.sofka.model.city.City;
import reactor.core.publisher.Flux;

public interface CityRepository {

    Flux<City> findByStateId(String stateId);

}
