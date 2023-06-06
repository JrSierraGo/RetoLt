package co.com.sofka.api.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class SofkianoDTO implements Serializable {

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
