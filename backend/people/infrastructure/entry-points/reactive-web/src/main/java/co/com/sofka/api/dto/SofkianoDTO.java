package co.com.sofka.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class SofkianoDTO {

    private String id;
    private String documentTypeId;
    private String documentNumber;
    private String name;
    private String lastName;
    private LocationDTO location;
    private Long entryDate;
    private String customerId;
    private String status;
    private List<SkillDTO> skills;
}
