package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.MaterialType;

import java.time.LocalDateTime;

public record StockMovement(WeightInTons amount, MovementType movementType, LocalDateTime timestamp, MaterialType materialType) {

}
