package be.kdg.prog6.Landside.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Truck {
    private final TruckId id;
    private final LicensePlate licensePlate;
    private final LocalDateTime arrivalTime;
    private boolean onTime;
    private TruckLocation location;

    public Truck(TruckId id, LicensePlate licensePlate, LocalDateTime arrivalTime, TruckLocation location) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.arrivalTime = arrivalTime;
        this.location = location;
        onTime = true;
    }

    public Truck(TruckId id, LicensePlate licensePlate, LocalDateTime arrivalTime, boolean onTime, TruckLocation location) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.arrivalTime = arrivalTime;
        this.onTime = onTime;
        this.location = location;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public boolean isOnTime() {
        return onTime;
    }

    public void setOnTime(boolean onTime) {
        this.onTime = onTime;
    }

    public TruckLocation getLocation() {
        return location;
    }

    public void setLocation(TruckLocation location) {
        this.location = location;
    }

    public TruckId getId() {
        return id;
    }

    public record TruckId(UUID id) {
    }
}
