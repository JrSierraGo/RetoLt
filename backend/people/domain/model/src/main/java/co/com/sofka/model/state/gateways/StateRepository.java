package co.com.sofka.model.state.gateways;

import co.com.sofka.model.state.State;
import reactor.core.publisher.Flux;

public interface StateRepository {

    Flux<State> findByCountryId(String countryId);

}
