package be.kdg.prog6.warehouse.adapters.out.db;

import be.kdg.prog6.warehouse.adapters.out.db.JpaEntities.StockMovementJpaEntity;
import be.kdg.prog6.warehouse.adapters.out.db.JpaEntities.WarehouseJpaEntity;
import be.kdg.prog6.warehouse.adapters.out.db.Repositories.WarehouseJpaRepository;
import be.kdg.prog6.warehouse.domain.*;
import be.kdg.prog6.warehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.warehouse.ports.out.SaveWarehousePort;
import be.kdg.prog6.warehouse.ports.out.UpdateWarehousePort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class WarehouseDatabaseAdapter implements LoadWarehousePort, UpdateWarehousePort, SaveWarehousePort {
    private static final Logger LOGGER = LogManager.getLogger(WarehouseDatabaseAdapter.class);
    private final WarehouseJpaRepository warehouseJpaRepository;
    private final StockMovementJpaRepository stockMovementRepository;

    public WarehouseDatabaseAdapter(WarehouseJpaRepository warehouseJpaRepository, StockMovementJpaRepository stockMovementRepository) {
        this.warehouseJpaRepository = warehouseJpaRepository;
        this.stockMovementRepository = stockMovementRepository;
    }

    @Override
    public Optional<Warehouse> loadWarehouseById(Warehouse.WarehouseId warehouseId) {
        LOGGER.info("Loading warehouse with id {}", warehouseId);
        return warehouseJpaRepository.findTopByWarehouseIdOrderByBaseStockTimestampDesc(warehouseId.id())
                .map(this::toWarehouse)
                .map(warehouse -> {
                    List<StockMovementJpaEntity> stockMovements = getStockMovements(warehouseId, warehouse.getBaseStockTimestamp());
                    stockMovements.forEach(stockMovementJpaEntity -> warehouse.addStockMovement(
                            new StockMovement(
                                    new WeightInTons(stockMovementJpaEntity.getAmount()),
                                    stockMovementJpaEntity.getMovementType(),
                                    stockMovementJpaEntity.getTimestamp(),
                                    stockMovementJpaEntity.getMaterialType()
                            )
                    ));
                    return warehouse;
                });
    }

    private List<StockMovementJpaEntity> getStockMovements(Warehouse.WarehouseId warehouseId, LocalDateTime baseStockTimestamp) {
        if (baseStockTimestamp != null) {
            return stockMovementRepository.findByWarehouseIdAndTimestampGreaterThan(warehouseId.id(), baseStockTimestamp);
        } else {
            return stockMovementRepository.findByWarehouseId(warehouseId.id());
        }
    }

    @Override
    public void saveWarehouse(Warehouse warehouse) {
        WarehouseJpaEntity entity = new WarehouseJpaEntity();
        entity.setWarehouseId(warehouse.getId().id());
        entity.setName(warehouse.getName());
        entity.setSellerId(warehouse.getSellerId().id());
        entity.setMaterialType(warehouse.getMaterialType());
        entity.setBaseStock(warehouse.getBaseStock().value());
        entity.setBaseStockTimestamp(warehouse.getBaseStockTimestamp());
        warehouseJpaRepository.save(entity);
    }

    @Override
    public List<Warehouse> findAll() {
        return warehouseJpaRepository.findAll().stream()
                .map(this::toWarehouse)
                .toList();
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        // Empty implementation because the snaspshoting is done with save to
    }

    private Warehouse toWarehouse(WarehouseJpaEntity entity) {
        return new Warehouse(
                entity.getName(),
                new Warehouse.WarehouseId(entity.getWarehouseId()),
                new SellerId(entity.getSellerId()),
                entity.getMaterialType(),
                new StockMovementWindow(),
                WeightInTons.of(entity.getBaseStock()),
                entity.getBaseStockTimestamp()
        );
    }


}
