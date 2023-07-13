package co.com.sofka.jpa.document;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPADocumentTypeRepository extends CrudRepository<DocumentTypeEntity, String>, QueryByExampleExecutor<DocumentTypeEntity> {
}
