package co.com.sofka.api.handler;

import co.com.sofka.api.dto.LocationDTO;
import co.com.sofka.domainconverter.DomainMapper;
import co.com.sofka.model.location.Location;
import co.com.sofka.usecase.handler.LocationHandlerUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/ms-people/location/query")
public class LocationQueryHandler extends DomainMapper<Location, LocationDTO> {

    private final LocationHandlerUseCase useCase;

    protected LocationQueryHandler(ObjectMapper mapper, LocationHandlerUseCase useCase) {
        super(mapper);
        this.useCase = useCase;
    }

    @GetMapping(
            path = "/by-sofkiano",
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Mono<LocationDTO> getLocationBySofkianoId(@RequestParam(name = "id") String sofkianoId)
    {
        return useCase.getLocationBySofkianoId(sofkianoId)
                .map(super::domainToDTO);
    }
}
