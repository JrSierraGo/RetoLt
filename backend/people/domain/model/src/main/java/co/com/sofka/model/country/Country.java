package co.com.sofka.model.country;
import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Country {
    private String id;
    private String name;
}
