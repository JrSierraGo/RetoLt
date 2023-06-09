package co.com.sofka.jpa.sofkiano;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name = "sofkiano")
public class SofkianoEntity {

    @Id
    private String id;
    @Column(name = "document_type_id")
    private String documentTypeId;
    @Column(name = "document_number")
    private String documentNumber;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "entry_date")
    private Long entryDate;
    @Column(name = "customer_id")
    private String customerId;
    private String status;
}
