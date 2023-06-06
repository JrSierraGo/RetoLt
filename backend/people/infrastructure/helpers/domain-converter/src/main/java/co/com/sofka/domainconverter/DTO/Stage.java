package co.com.sofka.domainconverter.DTO;

import lombok.Data;

import java.util.List;

@Data
public class Stage {
    private List<String> AllowedValues;
    private String Default;
    private String Description;
    private String Type;

}
