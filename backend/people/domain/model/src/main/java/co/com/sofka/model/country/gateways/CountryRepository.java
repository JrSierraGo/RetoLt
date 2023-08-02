package co.com.sofka.model.country.gateways;

import co.com.sofka.model.country.Country;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CountryRepository {

    Flux<Country> findAll();

    Mono<Country> findById(String id);
}
