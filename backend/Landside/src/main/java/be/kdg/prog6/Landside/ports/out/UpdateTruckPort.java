package be.kdg.prog6.Landside.ports.out;

import be.kdg.prog6.Landside.domain.Truck;

@FunctionalInterface
public interface UpdateTruckPort {
    void updateTruck(Truck truck);
}
