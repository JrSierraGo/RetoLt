package co.com.sofka.usecase.handler;

import co.com.sofka.enums.LocationStatusEnum;
import co.com.sofka.model.city.gateways.CityRepository;
import co.com.sofka.model.country.gateways.CountryRepository;
import co.com.sofka.model.location.Location;
import co.com.sofka.model.location.gateways.LocationRepository;
import co.com.sofka.model.state.gateways.StateRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class LocationHandlerUseCase {

    private final LocationRepository locationRepository;
    private final CityRepository cityRepository;
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    public Mono<Location> getLocationBySofkianoId(String sofkianoId) {
        return locationRepository.findBySofkianoIdAndStatus(sofkianoId, LocationStatusEnum.CURRENT.name())
                .flatMap(location -> cityRepository.findById(location.getCityId())
                        .map(city -> location.toBuilder()
                                .cityId(city.getId())
                                .cityName(city.getName())
                                .stateId(city.getStateId())
                                .build()
                        )
                )
                .flatMap(location -> stateRepository.findById(location.getStateId())
                        .map(state -> location.toBuilder()
                                .stateName(state.getName())
                                .countryId(state.getCountryId())
                                .build()
                        )
                )
                .flatMap(location -> countryRepository.findById(location.getCountryId())
                        .map(country -> location.toBuilder()
                                .countryName(country.getName())
                                .build()
                        )
                );
    }

}
