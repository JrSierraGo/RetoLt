package co.com.sofka.api.handler;

import co.com.sofka.model.sofkiano.SofkianoPageable;
import co.com.sofka.usecase.handler.SofkianoHandlerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/ms-people/sofkiano/query")
public class SofkianoQueryHandler {

    private final SofkianoHandlerUseCase useCase;

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
