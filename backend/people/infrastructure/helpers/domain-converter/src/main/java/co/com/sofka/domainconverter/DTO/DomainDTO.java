package co.com.sofka.domainconverter.DTO;

import com.sun.jdi.PrimitiveValue;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
public class DomainDTO {
    private String AWSTemplateFormatVersion;
    private String Transform;
    private String Description;
    private Parameters Parameters;
    private Resources Resources;
}
