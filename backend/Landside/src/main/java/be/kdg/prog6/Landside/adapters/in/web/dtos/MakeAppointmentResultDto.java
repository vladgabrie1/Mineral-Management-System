package be.kdg.prog6.Landside.adapters.in.web.dtos;

import be.kdg.prog6.common.domain.MaterialType;

import java.time.LocalDateTime;
import java.util.UUID;

public record MakeAppointmentResultDto(UUID sellerId, LocalDateTime startTime, LocalDateTime endTime,
                                       String licensePlate, MaterialType materialType, UUID warehouseId) {

}
