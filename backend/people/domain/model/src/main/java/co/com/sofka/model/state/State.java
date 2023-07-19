package co.com.sofka.model.state;
import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class State {
    private String id;
    private String name;
    private String countryId;
}
