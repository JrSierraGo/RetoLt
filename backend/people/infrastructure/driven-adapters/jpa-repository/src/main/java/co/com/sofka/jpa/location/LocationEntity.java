package co.com.sofka.jpa.location;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "location")
public class LocationEntity {
    @Id
    private String id;
    @Column(name = "city_id")
    private String cityId;
    private String address;
    @Column(name = "additional_indications")
    private String additionalIndications;
    private String neighborhood;
    @Column(name = "sofkiano_id")
    private String sofkianoId;
    private String status;
}
