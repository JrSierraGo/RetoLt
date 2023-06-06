package co.com.sofka.model.sofkiano;
import lombok.*;

import java.util.List;


@Data
@Builder(toBuilder = true)
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
    private List<Skill> skills;
}
