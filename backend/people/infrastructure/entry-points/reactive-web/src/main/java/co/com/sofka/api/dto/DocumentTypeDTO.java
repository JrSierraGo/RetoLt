package co.com.sofka.api.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class DocumentTypeDTO {
    private String id;
    private String acronym;
    private String name;
}
