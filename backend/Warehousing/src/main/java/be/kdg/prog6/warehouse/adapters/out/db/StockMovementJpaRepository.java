package be.kdg.prog6.warehouse.adapters.out.db;

import be.kdg.prog6.warehouse.adapters.out.db.JpaEntities.StockMovementJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface StockMovementJpaRepository extends JpaRepository<StockMovementJpaEntity, UUID> {
    List<StockMovementJpaEntity> findByWarehouseId(UUID warehouseId);

    List<StockMovementJpaEntity> findByWarehouseIdAndTimestampGreaterThan(UUID warehouseId, LocalDateTime timestamp);

}
