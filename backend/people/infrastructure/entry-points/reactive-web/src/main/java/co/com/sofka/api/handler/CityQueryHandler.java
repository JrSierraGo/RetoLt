package co.com.sofka.api.handler;

import co.com.sofka.api.dto.CityDTO;
import co.com.sofka.domainconverter.DomainMapper;
import co.com.sofka.model.city.City;
import co.com.sofka.usecase.handler.CityHandlerUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/api/ms-people/city/query")
public class CityQueryHandler extends DomainMapper<City, CityDTO> {

    private final CityHandlerUseCase useCase;

    protected CityQueryHandler(ObjectMapper mapper, CityHandlerUseCase useCase) {
        super(mapper);
        this.useCase = useCase;
    }


    @GetMapping(
            path = "/by-state",
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Flux<CityDTO> getByStateId(@RequestParam(name = "id") String stateId)
    {
        return useCase.getByStateId(stateId)
                .map(this::domainToDTO);
    }
}
