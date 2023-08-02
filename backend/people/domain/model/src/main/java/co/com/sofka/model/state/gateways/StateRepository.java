package co.com.sofka.model.state.gateways;

import co.com.sofka.model.state.State;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StateRepository {

    Flux<State> findByCountryId(String countryId);

    Mono<State> findById(String id);

}
