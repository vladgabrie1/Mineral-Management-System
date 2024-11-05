package be.kdg.prog6.Landside.ports.in.commands;

import be.kdg.prog6.Landside.domain.LicensePlate;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.Landside.domain.SellerId;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

public record MakeAppointmentCommand(SellerId sellerId, LocalDateTime time, LicensePlate licensePlate, MaterialType materialType){
    public MakeAppointmentCommand {
        requireNonNull(sellerId, "Seller ID is required");
        requireNonNull(time, "Appointment time is required");
        requireNonNull(licensePlate, "License plate is required");
        requireNonNull(materialType, "Material type is required");

        if (time.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment time must be in the future");
        }
    }
}
