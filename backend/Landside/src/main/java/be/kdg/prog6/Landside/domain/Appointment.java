package be.kdg.prog6.Landside.domain;

import java.time.LocalDateTime;
import java.util.UUID;
import be.kdg.prog6.common.domain.MaterialType;

import static java.util.Objects.requireNonNull;

public record Appointment(AppointmentId id, SellerId sellerId,
                          LicensePlate licensePlate, MaterialType materialType, AppointmentWindow appointmentWindow, WarehouseId warehouseId) {

    public record AppointmentId(UUID id) {
        public AppointmentId {
            requireNonNull(id);
        }
    }

    public Appointment{
        requireNonNull(sellerId);
        requireNonNull(licensePlate);
        requireNonNull(materialType);
        requireNonNull(appointmentWindow);
        requireNonNull(warehouseId);

        if (appointmentWindow.getStartTime().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Appointment window start time is before now");
        }
    }
}
