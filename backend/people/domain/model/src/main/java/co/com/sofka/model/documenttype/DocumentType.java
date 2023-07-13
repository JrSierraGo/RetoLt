package co.com.sofka.model.documenttype;
import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DocumentType {
    private String id;
    private String acronym;
    private String name;
}
