package co.com.sofka.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder(toBuilder = true)
@ToString
public class ErrorHandlerDTO {
    private String code;
    private String status;
    private String message;
}
