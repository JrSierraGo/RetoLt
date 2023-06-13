package co.com.sofka.api.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class SofkianoStatusDTO {
    private String sofkianoId;
    private Long entryDate;
    private String status;
}
