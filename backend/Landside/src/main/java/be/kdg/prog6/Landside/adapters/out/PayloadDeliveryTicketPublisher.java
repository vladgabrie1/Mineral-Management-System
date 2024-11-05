package be.kdg.prog6.Landside.adapters.out;

import be.kdg.prog6.Landside.domain.PayloadDeliveryTicket;
import be.kdg.prog6.Landside.ports.out.UpdatePayloadDeliveryTicketPort;
import be.kdg.prog6.common.events.RawMaterialsReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PayloadDeliveryTicketPublisher implements UpdatePayloadDeliveryTicketPort {
    private static final String EXCHANGE_NAME = "raw_materials_events";
    private static final Logger LOGGER = LoggerFactory.getLogger(PayloadDeliveryTicketPublisher.class);
    private final RabbitTemplate rabbitTemplate;

    public PayloadDeliveryTicketPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void updatePDT(PayloadDeliveryTicket pdt) {
        final String routingKey = "pdt." + pdt.getId() + ".created";

        LOGGER.info("Notifying RabbitMQ: {}", routingKey);

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, new RawMaterialsReceivedEvent(
                pdt.getId().id(),
                pdt.getWeight().value(),
                pdt.getLicensePlate().licensePlate(),
                pdt.getDeliveryTime(),
                pdt.getWarehouseId().id(),
                pdt.getMaterialType()
        ));
    }
}
