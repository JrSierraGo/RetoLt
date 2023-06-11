package co.com.sofka.model.location;
import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Location {
    private String id;
    private String cityId;
    private String address;
    private String neighborhood;
    private String additionalIndications;
    private String sofkianoId;
    private String status;
}
