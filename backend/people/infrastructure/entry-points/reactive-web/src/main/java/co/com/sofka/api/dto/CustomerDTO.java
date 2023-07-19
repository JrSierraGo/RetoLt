package co.com.sofka.api.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class CustomerDTO {

    private String id;
    private String name;
    private String nit;
    private String status;
}
