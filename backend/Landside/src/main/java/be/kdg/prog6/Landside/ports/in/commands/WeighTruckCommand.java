package be.kdg.prog6.Landside.ports.in.commands;

import be.kdg.prog6.Landside.domain.LicensePlate;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighingBridgeNumber;
import be.kdg.prog6.Landside.domain.WeightInTons;

import java.time.LocalDateTime;

public record WeighTruckCommand(LicensePlate licensePlate, WeighingBridgeNumber weighingBridgeNumber, WeightInTons weight, LocalDateTime timestamp) {
}
