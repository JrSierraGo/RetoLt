package co.com.sofka.model.documenttype.gateways;

import co.com.sofka.model.documenttype.DocumentType;
import reactor.core.publisher.Flux;

public interface DocumentTypeRepository {

    Flux<DocumentType> findAll();
}
