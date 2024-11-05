package be.kdg.prog6.Landside.adapters.out.db.JpaRepositories;

import be.kdg.prog6.Landside.adapters.out.db.JpaEntities.WarehouseLandsideJpaEntity;
import be.kdg.prog6.common.domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseLandsideJpaRepository extends JpaRepository<WarehouseLandsideJpaEntity, UUID> {

    Optional<WarehouseLandsideJpaEntity> findBySellerIdAndMaterialType(UUID sellerId, MaterialType materialType);
}
