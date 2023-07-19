package co.com.sofka.jpa.city;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "city")
public class CityEntity {
    @Id
    private String id;
    private String name;
    @Column(name = "state_id")
    private String stateId;
}
