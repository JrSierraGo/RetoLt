package co.com.sofka.api.dto;

import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class SkillDTO {
    private String id;
    private String skillId;
    private String customerId;
}
