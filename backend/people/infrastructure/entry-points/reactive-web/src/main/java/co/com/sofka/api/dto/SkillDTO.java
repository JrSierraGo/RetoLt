package co.com.sofka.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder(toBuilder = true)
@ToString
public class SkillDTO {
    private String id;
    private String skillId;
    private String customerId;
}
