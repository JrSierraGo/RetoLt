package co.com.sofka.usecase;

import co.com.sofka.model.Sofkiano;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SofkianoCommandUseCase {

    public Mono<Sofkiano> process(Sofkiano sofkiano){
        return null;
    }

}
