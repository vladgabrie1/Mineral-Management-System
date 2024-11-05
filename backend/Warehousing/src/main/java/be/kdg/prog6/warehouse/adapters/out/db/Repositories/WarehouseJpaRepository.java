package be.kdg.prog6.warehouse.adapters.out.db.Repositories;

import be.kdg.prog6.warehouse.adapters.out.db.JpaEntities.WarehouseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseJpaRepository extends JpaRepository<WarehouseJpaEntity, UUID> {
    Optional<WarehouseJpaEntity> findTopByWarehouseIdOrderByBaseStockTimestampDesc(UUID warehouseId);
}
