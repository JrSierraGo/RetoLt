package co.com.sofka.jpa.customer;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    private String id;
    private String name;
    private String nit;
    private String status;
}
