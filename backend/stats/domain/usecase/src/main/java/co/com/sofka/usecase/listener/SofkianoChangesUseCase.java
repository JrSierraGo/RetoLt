package co.com.sofka.usecase.listener;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SofkianoChangesUseCase {

    public Mono<Void> process(){
        return Mono.empty();
    }
}
