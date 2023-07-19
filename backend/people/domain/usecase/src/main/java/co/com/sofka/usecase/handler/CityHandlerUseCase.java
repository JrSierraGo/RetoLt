package co.com.sofka.usecase.handler;

import co.com.sofka.model.city.City;
import co.com.sofka.model.city.gateways.CityRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class CityHandlerUseCase {

    private final CityRepository cityRepository;

    public Flux<City> getByStateId(String stateId) {
        return cityRepository.findByStateId(stateId);
    }
}
