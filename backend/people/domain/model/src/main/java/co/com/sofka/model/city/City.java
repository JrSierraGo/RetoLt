package co.com.sofka.model.city;
import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class City {
    private String id;
    private String name;
    private String stateId;
}
