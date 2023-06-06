package co.com.sofka.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder(toBuilder = true)
@ToString
public class SofkianoDTO {

    private String id;
    private String documentTypeId;
    private String documentNumber;
    private String name;
    private String lastName;
    private String locationId;
    private Long entryDate;
    private String customerId;
    private String status;
    private List<SkillDTO> skills;
}
