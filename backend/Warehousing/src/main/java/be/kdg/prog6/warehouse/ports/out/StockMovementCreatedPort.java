package be.kdg.prog6.warehouse.ports.out;

import be.kdg.prog6.warehouse.domain.StockMovement;
import be.kdg.prog6.warehouse.domain.Warehouse;

@FunctionalInterface
public interface StockMovementCreatedPort {
    void stockMovementCreated(StockMovement stockMovement, Warehouse.WarehouseId warehouseId);
}
