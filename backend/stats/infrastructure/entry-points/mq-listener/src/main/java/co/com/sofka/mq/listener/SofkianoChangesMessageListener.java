package co.com.sofka.mq.listener;

import co.com.sofka.mq.dto.MessageRabbitDTO;
import co.com.sofka.usecase.listener.SofkianoChangesUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SofkianoChangesMessageListener {

    private final SofkianoChangesUseCase useCase;

    @RabbitListener(queues = "${broker.queues.sofkianoChanges}")
    public void process(MessageRabbitDTO message) throws Exception {
        try {
            log.info(message.toString());
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }
}
