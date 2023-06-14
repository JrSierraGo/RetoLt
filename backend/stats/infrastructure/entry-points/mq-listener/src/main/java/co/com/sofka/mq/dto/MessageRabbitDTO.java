package co.com.sofka.mq.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MessageRabbitDTO implements Serializable {

    private String id;
    private String name;

}
