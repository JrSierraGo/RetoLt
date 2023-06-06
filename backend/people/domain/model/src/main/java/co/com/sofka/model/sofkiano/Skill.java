package co.com.sofka.model.sofkiano;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder(toBuilder = true)
@ToString
public class Skill {
    private String id;
    private String sofkianoId;
    private String skillId;
    private String customerId;
}
