package co.com.sofka.model.country.gateways;

import co.com.sofka.model.country.Country;
import reactor.core.publisher.Flux;

public interface CountryRepository {

    Flux<Country> findAll();
}
