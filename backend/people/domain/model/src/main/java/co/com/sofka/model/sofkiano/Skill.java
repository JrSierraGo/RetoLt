package co.com.sofka.model.sofkiano;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class Skill {
    private String id;
    private String sofkianoId;
    private String skillId;
    private String customerId;
}
