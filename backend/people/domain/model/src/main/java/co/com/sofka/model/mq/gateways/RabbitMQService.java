package co.com.sofka.model.mq.gateways;

import reactor.core.publisher.Mono;

public interface RabbitMQService<E> {

    Mono<Void> sendMessage(E payload);
}
