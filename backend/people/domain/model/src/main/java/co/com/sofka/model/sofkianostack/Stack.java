package co.com.sofka.model.sofkianostack;
import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Stack {
    private String id;
    private String sofkianoId;
    private String skillId;
    private String customerId;
}
