package be.kdg.prog6.Landside.adapters.out.db;

import be.kdg.prog6.Landside.adapters.out.db.JpaEntities.TruckJpaEntity;
import be.kdg.prog6.Landside.adapters.out.db.JpaRepositories.TruckJpaRepository;
import be.kdg.prog6.Landside.domain.LicensePlate;
import be.kdg.prog6.Landside.domain.Truck;
import be.kdg.prog6.Landside.domain.TruckLocation;
import be.kdg.prog6.Landside.ports.out.LoadTruckPort;
import be.kdg.prog6.Landside.ports.out.UpdateTruckPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
public class TruckDatabaseAdapter implements UpdateTruckPort, LoadTruckPort {
    private final TruckJpaRepository truckJpaRepository;

    public TruckDatabaseAdapter(TruckJpaRepository truckJpaRepository) {
        this.truckJpaRepository = truckJpaRepository;
    }

    @Override
    public void updateTruck(Truck truck) {
        final TruckJpaEntity jpaTruck = truckJpaRepository.findByLicensePlateAndLocationIsNot(truck.getLicensePlate().licensePlate(), TruckLocation.EXITED)
                .orElseGet(() -> new TruckJpaEntity(truck.getId().id(), truck.getLicensePlate().licensePlate(), truck.getArrivalTime(), truck.isOnTime(), truck.getLocation()));

       jpaTruck.setLocation(truck.getLocation());
       truckJpaRepository.save(jpaTruck);
    }

    @Override
    public Optional<Truck> loadOnSiteTruckByLicensePlate(LicensePlate licensePlate) {
        final Optional<TruckJpaEntity> jpaTruck =  truckJpaRepository.findByLicensePlateAndLocationIsNot(licensePlate.licensePlate(), TruckLocation.EXITED);
        return jpaTruck.map(truckJpaEntity -> new Truck(new Truck.TruckId(jpaTruck.get().getId()), new LicensePlate(truckJpaEntity.getLicensePlate()), truckJpaEntity.getArrivalTime(), truckJpaEntity.isOnTime(), truckJpaEntity.getLocation()));
    }

    @Override
    public List<Truck> loadAllTrucksForToday() {
        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);
        List<TruckJpaEntity> truckJpaEntities = truckJpaRepository.findByArrivalTimeBetween(startOfDay, endOfDay);
        return truckJpaEntities.stream().map(truckJpaEntity -> new Truck(new Truck.TruckId(truckJpaEntity.getId()),new LicensePlate(truckJpaEntity.getLicensePlate()), truckJpaEntity.getArrivalTime(), truckJpaEntity.isOnTime(), truckJpaEntity.getLocation())).toList();
    }

}
