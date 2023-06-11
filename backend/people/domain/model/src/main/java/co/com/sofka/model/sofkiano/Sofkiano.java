package co.com.sofka.model.sofkiano;

import co.com.sofka.model.location.Location;
import co.com.sofka.model.sofkianostack.Stack;
import lombok.*;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class Sofkiano {
    private String id;
    private String documentTypeId;
    private String documentNumber;
    private String name;
    private String lastName;
    private Location location;
    private Long entryDate;
    private String customerId;
    private String status;
    private List<Stack> stacks;
}
