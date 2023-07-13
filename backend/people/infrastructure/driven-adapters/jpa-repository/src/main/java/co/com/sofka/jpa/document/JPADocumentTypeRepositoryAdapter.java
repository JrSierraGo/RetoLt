package co.com.sofka.jpa.document;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.documenttype.DocumentType;
import co.com.sofka.model.documenttype.gateways.DocumentTypeRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JPADocumentTypeRepositoryAdapter extends AdapterOperations<DocumentTypeEntity, DocumentType, String, JPADocumentTypeRepository> implements DocumentTypeRepository {

    protected JPADocumentTypeRepositoryAdapter(JPADocumentTypeRepository repository, ObjectMapper mapper) {
        super(repository, mapper);
    }
}
