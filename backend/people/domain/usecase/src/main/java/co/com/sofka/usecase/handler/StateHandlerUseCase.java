package co.com.sofka.usecase.handler;

import co.com.sofka.model.state.State;
import co.com.sofka.model.state.gateways.StateRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class StateHandlerUseCase {

    private final StateRepository stateRepository;

    public Flux<State> getByCountryId(String countryId) {
        return stateRepository.findByCountryId(countryId);
    }
}
