package be.kdg.prog6.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingTopology {
    private static final String WAREHOUSE_EVENTS_EXCHANGE = "warehouse_events";
    private static final String WAREHOUSE_CAPACITY_UPDATES_QUEUE = "warehouse_capacity_updates";
    private static final String WAREHOUSE_CREATED_QUEUE = "warehouse_created";
    private static final String RAW_MATERIALS_RECEIVED_QUEUE = "raw_materials_received";
    private static final String RAW_MATERIALS_EXCHANGE = "raw_materials_events";

    @Bean
    Queue warehouseUpdatesQueue() {
        return new Queue(WAREHOUSE_CAPACITY_UPDATES_QUEUE, false);
    }

    @Bean
    Queue rawMaterialReceivedQueue() {
        return new Queue(RAW_MATERIALS_RECEIVED_QUEUE, false);
    }
    @Bean
    Queue warehouseCreatedQueue(){
        return new Queue(WAREHOUSE_CREATED_QUEUE, false);
    }

    @Bean
    TopicExchange rawMaterialsEventsExchange() {
        return new TopicExchange(RAW_MATERIALS_EXCHANGE);
    }

    @Bean
    TopicExchange warehouseEventsExchange() {
        return new TopicExchange(WAREHOUSE_EVENTS_EXCHANGE);
    }

    @Bean
    Binding warehouseCapacityUpdateBinding(Queue warehouseUpdatesQueue, TopicExchange warehouseEventsExchange) {
        return BindingBuilder
                .bind(warehouseUpdatesQueue)
                .to(warehouseEventsExchange)
                .with("warehouse.#.updated");
    }

    @Bean
    Binding warehouseCreatedBinding(Queue warehouseCreatedQueue, TopicExchange warehouseEventsExchange) {
        return BindingBuilder
                .bind(warehouseCreatedQueue)
                .to(warehouseEventsExchange)
                .with("warehouse.#.created");
    }

    @Bean
    Binding pdtCreatedBinding(Queue rawMaterialReceivedQueue, TopicExchange rawMaterialsEventsExchange) {
        return BindingBuilder
                .bind(rawMaterialReceivedQueue)
                .to(rawMaterialsEventsExchange)
                .with("pdt.#.created");
    }


    @Bean
    Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, Jackson2JsonMessageConverter producerJackson2MessageConverter) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter);
        return rabbitTemplate;
    }
}
