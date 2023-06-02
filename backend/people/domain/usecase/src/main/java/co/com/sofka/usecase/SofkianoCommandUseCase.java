package co.com.sofka.usecase;

import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SofkianoCommandUseCase {

    private final SofkianoRepository sofkianoRepository;

    public Mono<Sofkiano> process(Sofkiano sofkiano){
        return sofkianoRepository.save(sofkiano);
    }

}
