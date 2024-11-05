package be.kdg.prog6.Landside.ports.out;

import be.kdg.prog6.Landside.domain.LicensePlate;
import be.kdg.prog6.Landside.domain.Truck;

import java.util.List;
import java.util.Optional;


public interface LoadTruckPort {
    Optional<Truck> loadOnSiteTruckByLicensePlate(LicensePlate licensePlate);
    List<Truck> loadAllTrucksForToday();
}
