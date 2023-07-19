package co.com.sofka.jpa.state;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "state")
public class StateEntity {

    @Id
    private String id;
    private String name;
    @Column(name = "country_id")
    private String countryId;
}
