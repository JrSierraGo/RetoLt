package co.com.sofka.jpa.document;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "document_type")
public class DocumentTypeEntity {

    @Id
    private String id;
    private String acronym;
    private String name;
}
