package be.kdg.prog6.warehouse.ports.in;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.domain.WeightInTons;

import java.time.LocalDateTime;

public record ReceiveRawMaterialCommand(Warehouse.WarehouseId warehouseId, WeightInTons amount, MaterialType materialType, LocalDateTime deliveryTime) {
}
