package be.kdg.prog6.Landside.adapters.in.web.dtos;

import java.time.LocalDateTime;

public record TruckDto(String licensePlate, LocalDateTime arrivalTime, boolean onTime, String location) {
}
