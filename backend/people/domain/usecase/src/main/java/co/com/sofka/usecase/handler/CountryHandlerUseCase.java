package co.com.sofka.usecase.handler;

import co.com.sofka.model.country.Country;
import co.com.sofka.model.country.gateways.CountryRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class CountryHandlerUseCase {

    private final CountryRepository countryRepository;

    public Flux<Country> getAll(){
        return countryRepository.findAll();
    }
}
