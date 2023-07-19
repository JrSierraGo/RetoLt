package co.com.sofka.usecase.handler;

import co.com.sofka.model.location.Location;
import co.com.sofka.model.location.gateways.LocationRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class LocationHandlerUseCase {

    private final LocationRepository locationRepository;

    public Mono<Location> getLocationBySofkianoId(String sofkianoId) {
        return locationRepository.findBySofkianoId(sofkianoId);
    }

}
