package be.kdg.prog6.Landside.ports.in;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.Landside.domain.SellerId;
import be.kdg.prog6.Landside.domain.WarehouseId;

@FunctionalInterface
public interface WarehouseCapacityProjector {

    void projectCapacity(WarehouseId warehouseId, double warehouseFillPercentage, SellerId sellerId, MaterialType materialType);

}
