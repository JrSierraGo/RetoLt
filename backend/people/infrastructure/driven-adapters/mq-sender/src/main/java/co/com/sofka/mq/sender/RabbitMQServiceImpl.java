package co.com.sofka.mq.sender;

import co.com.sofka.model.mq.gateways.RabbitMQService;
import co.com.sofka.model.sofkiano.Sofkiano;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQServiceImpl implements RabbitMQService<Sofkiano> {

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.exchanges.stats}")
    private String exchangeStats;

    @Value("${broker.routingKeys.sofkianoChanges}")
    private String routingKeySofkianoChanges;


    @Async
    @Override
    public Mono<Void> sendMessage(Sofkiano payload) {
        log.info("::::Start RabbitMQServiceImpl.sendMessage {}", payload);
        rabbitTemplate.convertAndSend(exchangeStats, routingKeySofkianoChanges, payload);
        return Mono.empty();
    }
}
