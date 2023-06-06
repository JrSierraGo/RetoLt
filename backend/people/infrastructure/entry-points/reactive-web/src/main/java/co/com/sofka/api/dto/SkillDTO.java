package co.com.sofka.api.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class SkillDTO {
    private String id;
    private String skillId;
    private String customerId;
}
