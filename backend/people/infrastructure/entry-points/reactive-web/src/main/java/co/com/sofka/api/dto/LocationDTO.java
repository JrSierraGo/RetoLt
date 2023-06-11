package co.com.sofka.api.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class LocationDTO {

    private String id;
    private String cityId;
    private String address;
    private String neighborhood;
    private String additionalIndications;
}
