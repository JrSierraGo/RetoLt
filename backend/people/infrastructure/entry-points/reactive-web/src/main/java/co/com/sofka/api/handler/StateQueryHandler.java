package co.com.sofka.api.handler;

import co.com.sofka.api.dto.StateDTO;
import co.com.sofka.domainconverter.DomainMapper;
import co.com.sofka.model.state.State;
import co.com.sofka.usecase.handler.StateHandlerUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/api/ms-people/state/query")
public class StateQueryHandler extends DomainMapper<State, StateDTO> {

    private final StateHandlerUseCase useCase;

    protected StateQueryHandler(ObjectMapper mapper, StateHandlerUseCase useCase) {
        super(mapper);
        this.useCase = useCase;
    }

    @GetMapping(
            path = "/by-country",
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Flux<StateDTO> getAll(@RequestParam(name = "id") String countryId)
    {
        return useCase.getByCountryId(countryId)
                .map(this::domainToDTO);
    }
}
