package co.com.sofka.api.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class StateDTO {

    private String id;
    private String name;
    private String countryId;
}
