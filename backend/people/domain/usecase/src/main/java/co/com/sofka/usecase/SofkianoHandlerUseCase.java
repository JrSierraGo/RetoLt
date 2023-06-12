package co.com.sofka.usecase;

import co.com.sofka.model.sofkiano.SofkianoPageable;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@RequiredArgsConstructor
public class SofkianoHandlerUseCase {

    private final SofkianoRepository sofkianoRepository;

    private static final String SORT_PARAM = "name";

    Logger log = Logger.getLogger(this.getClass().getName());

    public Mono<SofkianoPageable> getAll(Integer page, Integer size) {
        return sofkianoRepository.findAll(page, size, SORT_PARAM);
    }
}
