package co.com.sofka.api.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class CityDTO {
    private String id;
    private String name;
    private String stateId;
}
