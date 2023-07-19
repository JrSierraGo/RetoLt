package co.com.sofka.api.handler;

import co.com.sofka.api.dto.CountryDTO;
import co.com.sofka.domainconverter.DomainMapper;
import co.com.sofka.model.country.Country;
import co.com.sofka.usecase.handler.CountryHandlerUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/api/ms-people/country/query")
public class CountryQueryHandler extends DomainMapper<Country, CountryDTO> {


    private final CountryHandlerUseCase useCase;

    protected CountryQueryHandler(ObjectMapper mapper, CountryHandlerUseCase useCase) {
        super(mapper);
        this.useCase = useCase;
    }

    @GetMapping(
            path = "/all",
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Flux<CountryDTO> getAll() {
        return useCase.getAll()
                .map(this::domainToDTO);
    }
}
