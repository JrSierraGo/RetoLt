package co.com.sofka.model.sofkiano.gateways;

import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.SofkianoPageable;
import reactor.core.publisher.Mono;

public interface SofkianoRepository {

    Mono<Sofkiano> save(Sofkiano sofkiano);

    Mono<SofkianoPageable> findAll(Integer page, Integer size, String sortParam);

    Mono<Sofkiano> findById(String id);

}
