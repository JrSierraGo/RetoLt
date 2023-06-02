package co.com.sofka.model.sofkiano.gateways;

import co.com.sofka.model.sofkiano.Sofkiano;
import reactor.core.publisher.Mono;

public interface SofkianoRepository {

    Mono<Sofkiano> save(Sofkiano sofkiano);

}
