package co.com.sofka.jpa.sofkianostack;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sofkiano_stack")
public class SofkianoStackEntity {

    @Id
    private String id;
    @Column(name = "sofkiano_id")
    private String sofkianoId;
    @Column(name = "skill_id")
    private String skillId;
    @Column(name = "customer_id")
    private String customerId;
}
