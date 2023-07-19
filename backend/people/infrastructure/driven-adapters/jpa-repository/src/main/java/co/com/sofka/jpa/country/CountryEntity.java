package co.com.sofka.jpa.country;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "country")
public class CountryEntity {

    @Id
    private String id;
    private String name;
}
