package co.com.sofka.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder(toBuilder = true)
@ToString
@Data
public class Sofkiano {
    private String id;
    private String documentTypeId;
    private String documentNumber;
    private String name;
    private String lastName;
    private String locationId;
    private Long entryDate;
    private String customerId;
    private String status;
}
