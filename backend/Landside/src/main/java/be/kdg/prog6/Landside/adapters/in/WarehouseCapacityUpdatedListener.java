package be.kdg.prog6.Landside.adapters.in;

import be.kdg.prog6.Landside.domain.SellerId;
import be.kdg.prog6.Landside.domain.WarehouseId;
import be.kdg.prog6.Landside.ports.in.WarehouseCapacityProjector;
import be.kdg.prog6.common.events.WarehouseCapacityUpdatedEvent;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WarehouseCapacityUpdatedListener {
    private static final String WAREHOUSE_CAPACITY_UPDATES_QUEUE = "warehouse_capacity_updates";
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(WarehouseCapacityUpdatedListener.class);


    private final WarehouseCapacityProjector warehouseCapacityProjector;

    public WarehouseCapacityUpdatedListener(WarehouseCapacityProjector warehouseCapacityProjector) {
        this.warehouseCapacityProjector = warehouseCapacityProjector;
    }

    @RabbitListener(queues = WAREHOUSE_CAPACITY_UPDATES_QUEUE)
    public void warehouseCapacityUpdated(final WarehouseCapacityUpdatedEvent event) {
        LOGGER.info(
                "Warehouse {} capacity got updated to {}",
                event.warehouseId(),
                event.warehouseFillPercentage()

        );
        warehouseCapacityProjector.projectCapacity(new WarehouseId(event.warehouseId()), event.warehouseFillPercentage(), new SellerId(event.sellerId()), event.materialType());

    }

}
