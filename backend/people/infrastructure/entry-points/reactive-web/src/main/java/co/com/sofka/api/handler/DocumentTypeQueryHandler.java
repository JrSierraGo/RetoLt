package co.com.sofka.api.handler;

import co.com.sofka.api.dto.DocumentTypeDTO;
import co.com.sofka.domainconverter.DomainMapper;
import co.com.sofka.model.documenttype.DocumentType;
import co.com.sofka.usecase.handler.DocumentTypeHandlerUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/api/ms-people/document-type/query")
public class DocumentTypeQueryHandler extends DomainMapper<DocumentType, DocumentTypeDTO> {

    private final DocumentTypeHandlerUseCase useCase;

    protected DocumentTypeQueryHandler(ObjectMapper mapper, DocumentTypeHandlerUseCase useCase) {
        super(mapper);
        this.useCase = useCase;
    }

    @GetMapping(
            path = "/all",
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Flux<DocumentTypeDTO> getAll() {
        return useCase.getAll()
                .map(this::domainToDTO);
    }
}
