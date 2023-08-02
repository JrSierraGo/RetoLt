package co.com.sofka.model.location.gateways;

import co.com.sofka.model.location.Location;
import reactor.core.publisher.Mono;

public interface LocationRepository {
    Mono<Location> save(Location location);
    Mono<Location> findById(String id);
    Mono<Location> findByStatusAndIdNot(String status, String id);

    Mono<Location> findBySofkianoIdAndStatus(String sofkianoId, String status);
}
