package be.kdg.prog6.common.events;

import be.kdg.prog6.common.domain.MaterialType;

import java.util.UUID;

public record WarehouseCreatedEvent(UUID warehouseId, UUID sellerId, MaterialType materialType) {

}
