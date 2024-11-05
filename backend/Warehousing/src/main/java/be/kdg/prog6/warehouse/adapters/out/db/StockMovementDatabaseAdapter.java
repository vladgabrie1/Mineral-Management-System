package be.kdg.prog6.warehouse.adapters.out.db;

import be.kdg.prog6.warehouse.adapters.out.db.JpaEntities.StockMovementJpaEntity;
import be.kdg.prog6.warehouse.domain.StockMovement;
import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.ports.out.StockMovementCreatedPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StockMovementDatabaseAdapter implements StockMovementCreatedPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockMovementDatabaseAdapter.class);
    private final StockMovementJpaRepository stockMovementJpaRepository;

    public StockMovementDatabaseAdapter(StockMovementJpaRepository stockMovementJpaRepository) {
        this.stockMovementJpaRepository = stockMovementJpaRepository;
    }

    @Override
    public void stockMovementCreated(StockMovement stockMovement, Warehouse.WarehouseId warehouseId) {
        LOGGER.info("Stock movement created: {}", stockMovement);
        stockMovementJpaRepository.save(new StockMovementJpaEntity(
                UUID.randomUUID(),
                warehouseId.id(),
                stockMovement.amount().value(),
                stockMovement.movementType(),
                stockMovement.timestamp(),
                stockMovement.materialType()
        ));

    }


}
