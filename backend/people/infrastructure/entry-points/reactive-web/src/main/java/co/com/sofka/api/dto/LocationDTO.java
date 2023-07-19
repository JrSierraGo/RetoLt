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
    private String cityName;
    private String countryId;
    private String countryName;
    private String stateId;
    private String stateName;
    private String address;
    private String neighborhood;
    private String additionalIndications;
}
