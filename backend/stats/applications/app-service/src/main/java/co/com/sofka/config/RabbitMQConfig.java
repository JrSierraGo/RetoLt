package co.com.sofka.config;

import co.com.sofka.mq.listener.SofkianoChangesMessageListener;
import co.com.sofka.usecase.listener.SofkianoChangesUseCase;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;
    @Value("${spring.rabbitmq.username}")
    private String rabbitUserName;
    @Value("${spring.rabbitmq.password}")
    private String rabbitPassword;
    @Value("${broker.exchanges.stats}")
    private String exchangeStats;
    @Value("${broker.queues.sofkianoChanges}")
    private String queueSofkianoChanges;
    @Value("${broker.queues.sofkianoChangesDLQ}")
    private String queueSofkianoChangesDLQ;
    @Value("${broker.routingKeys.sofkianoChangesError}")
    private String routingKeySofkianoChangesError;
    @Value("${broker.routingKeys.sofkianoChanges}")
    private String routingKeySofkianoChanges;

    @Bean
    DirectExchange exchangeStats() {
        return new DirectExchange(exchangeStats);
    }

    @Bean
    Queue queueSofkianoChanges() {
        return QueueBuilder.durable(queueSofkianoChanges)
                .withArgument("x-dead-letter-exchange", exchangeStats)
                .withArgument("x-dead-letter-routing-key", routingKeySofkianoChangesError)
                .build();
    }

    @Bean
    Queue queueSofkianoChangesDlq() {
        return QueueBuilder.durable(queueSofkianoChangesDLQ)
                .build();
    }

    @Bean
    Binding bindingQueueSofkianoChanges() {
        return BindingBuilder
                .bind(queueSofkianoChanges())
                .to(exchangeStats())
                .with(routingKeySofkianoChanges);
    }

    @Bean
    Binding bindingSofkianoChangesDlq() {
        return BindingBuilder
                .bind(queueSofkianoChangesDlq())
                .to(exchangeStats())
                .with(routingKeySofkianoChangesError);
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        //connectionFactory.setUri("amqps://" + rabbitUserName + ":" + rabbitPassword + "@" + host + ":" + port);
        return connectionFactory;
    }

    @Bean
    RabbitTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
    @Bean
    SofkianoChangesMessageListener listener(SofkianoChangesUseCase useCase) {
        return new SofkianoChangesMessageListener(useCase);
    }
}
