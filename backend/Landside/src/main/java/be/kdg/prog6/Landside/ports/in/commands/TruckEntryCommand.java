package be.kdg.prog6.Landside.ports.in.commands;

import be.kdg.prog6.Landside.domain.LicensePlate;

import java.time.LocalDateTime;

public record TruckEntryCommand(LicensePlate licensePlate, LocalDateTime arrivalTime ) {
}
