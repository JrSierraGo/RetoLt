package co.com.sofka.usecase.handler;

import co.com.sofka.model.documenttype.DocumentType;
import co.com.sofka.model.documenttype.gateways.DocumentTypeRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class DocumentTypeHandlerUseCase {

    private final DocumentTypeRepository documentTypeRepository;

    public Flux<DocumentType> getAll(){
        return documentTypeRepository.findAll();
    }

}
