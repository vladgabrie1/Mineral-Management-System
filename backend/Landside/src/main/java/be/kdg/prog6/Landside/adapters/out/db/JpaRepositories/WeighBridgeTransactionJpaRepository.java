package be.kdg.prog6.Landside.adapters.out.db.JpaRepositories;

import be.kdg.prog6.Landside.adapters.out.db.JpaEntities.WeighBridgeTransactionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WeighBridgeTransactionJpaRepository extends JpaRepository<WeighBridgeTransactionJpaEntity, UUID> {
    Optional<WeighBridgeTransactionJpaEntity> findByLicensePlateAndNetWeightIsNull(String licensePlate);
}
