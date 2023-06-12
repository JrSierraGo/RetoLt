package co.com.sofka.model.sofkiano;

import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class SofkianoPageable {

    private Integer totalElements;
    private Integer totalPages;
    private List<Sofkiano> sofkianos;
}
