package be.kdg.prog6.common.events;


import be.kdg.prog6.common.domain.MaterialType;

import java.time.LocalDateTime;
import java.util.UUID;

public record RawMaterialsReceivedEvent(
    UUID id,
    double weightInTons,
    String licensePlate,
    LocalDateTime deliveryTime,
    UUID warehouseId,
    MaterialType materialType
) {}

