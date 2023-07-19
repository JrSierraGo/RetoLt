package co.com.sofka.api.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class CountryDTO {
    private String id;
    private String name;
}
