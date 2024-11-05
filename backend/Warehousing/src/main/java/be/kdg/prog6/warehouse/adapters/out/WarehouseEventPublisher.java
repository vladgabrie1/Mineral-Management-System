package be.kdg.prog6.warehouse.adapters.out;

import be.kdg.prog6.common.events.WarehouseCapacityUpdatedEvent;
import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.ports.out.UpdateWarehousePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class WarehouseEventPublisher implements UpdateWarehousePort {

    private static final String EXCHANGE_NAME = "warehouse_events";
    private static final Logger log = LoggerFactory.getLogger(WarehouseEventPublisher.class);


    private final RabbitTemplate rabbitTemplate;

    public WarehouseEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        final String routingKey = "warehouse." + warehouse.getId() + ".updated";

        log.info("Notifying RabbitMQ: {}", routingKey);

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, new WarehouseCapacityUpdatedEvent(
                warehouse.getId().id(),
                warehouse.getFillPercentage(),
                warehouse.getSellerId().id(),
                warehouse.getMaterialType()
        ));
    }
}
