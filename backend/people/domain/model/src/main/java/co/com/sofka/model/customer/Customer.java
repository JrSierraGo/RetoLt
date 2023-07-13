package co.com.sofka.model.customer;
import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Customer {
    private String id;
    private String name;
    private String nit;
    private String status;
}
