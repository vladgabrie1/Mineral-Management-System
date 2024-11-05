package be.kdg.prog6.warehouse.ports.in;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.warehouse.domain.SellerId;
import be.kdg.prog6.warehouse.domain.Warehouse;

public interface WarehouseCreatedUseCase {
    public void saveCreatedWarehouse(Warehouse.WarehouseId warehouseId, SellerId sellerId, MaterialType materialType);
}
