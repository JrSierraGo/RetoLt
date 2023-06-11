package co.com.sofka.model.sofkianostack.gateways;

import co.com.sofka.model.sofkianostack.Stack;
import reactor.core.publisher.Flux;

import java.util.List;

public interface SofkianoStackRepository {

    Flux<Stack> saveAll(List<Stack> stacks);

}
