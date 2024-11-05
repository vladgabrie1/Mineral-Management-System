package be.kdg.prog6.common.events;

import be.kdg.prog6.common.domain.MaterialType;

import java.util.UUID;

public record WarehouseCapacityUpdatedEvent(UUID warehouseId, double warehouseFillPercentage, UUID sellerId, MaterialType materialType) {

}
