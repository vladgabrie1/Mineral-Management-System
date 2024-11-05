package be.kdg.prog6.warehouse.adapters.in;

import be.kdg.prog6.common.events.RawMaterialsReceivedEvent;
import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.domain.WeightInTons;
import be.kdg.prog6.warehouse.ports.in.ReceiveRawMaterialCommand;
import be.kdg.prog6.warehouse.ports.in.ReceiveRawMaterialUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RawMaterialsReceivedListener {
    private static final String RAW_MATERIALS_RECEIVED_QUEUE = "raw_materials_received";
    private static final Logger LOGGER = LoggerFactory.getLogger(RawMaterialsReceivedListener.class);
    private final ReceiveRawMaterialUseCase receiveRawMaterialUseCase;

    public RawMaterialsReceivedListener(ReceiveRawMaterialUseCase receiveRawMaterialUseCase) {
        this.receiveRawMaterialUseCase = receiveRawMaterialUseCase;
    }

    @RabbitListener(queues = RAW_MATERIALS_RECEIVED_QUEUE)
    public void rawMaterialReceived(final RawMaterialsReceivedEvent event) {
        LOGGER.info("Received payload delivery ticket created event: {}", event);
        receiveRawMaterialUseCase.receiveRawMaterial(new ReceiveRawMaterialCommand(
                        new Warehouse.WarehouseId(event.warehouseId()),
                        WeightInTons.of(event.weightInTons()),
                        event.materialType(),
                        event.deliveryTime()
                )
        );
    }
}
