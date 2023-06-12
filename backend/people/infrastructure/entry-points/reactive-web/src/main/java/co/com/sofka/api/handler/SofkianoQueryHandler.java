package co.com.sofka.api.handler;

import co.com.sofka.api.dto.SofkianoDTO;
import co.com.sofka.domainconverter.DomainMapper;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.SofkianoPageable;
import co.com.sofka.usecase.SofkianoHandlerUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/ms-people/sofkiano/query")
public class SofkianoQueryHandler extends DomainMapper<Sofkiano, SofkianoDTO> {

    private final SofkianoHandlerUseCase useCase;

    protected SofkianoQueryHandler(ObjectMapper mapper, SofkianoHandlerUseCase useCase) {
        super(mapper);
        this.useCase = useCase;
    }

    @GetMapping(
            path = "/all",
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Mono<SofkianoPageable> getAll(@RequestParam(name = "page") Integer page,
                                         @RequestParam(name = "size") Integer size)
    {
        return useCase.getAll(page, size);
    }

}
