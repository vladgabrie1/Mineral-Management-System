package be.kdg.prog6.Landside.adapters.out.db.JpaRepositories;

import be.kdg.prog6.Landside.adapters.out.db.JpaEntities.TruckJpaEntity;
import be.kdg.prog6.Landside.domain.TruckLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TruckJpaRepository extends JpaRepository<TruckJpaEntity, UUID> {
    Optional<TruckJpaEntity> findByLicensePlate(String licensePlate);
    Optional<TruckJpaEntity> findByLicensePlateAndLocationIsNot(String licencePlate, TruckLocation location);
    List<TruckJpaEntity> findByArrivalTimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

}
