package be.kdg.prog6.warehouse.adapters.out.db.Repositories;

import be.kdg.prog6.warehouse.adapters.out.db.JpaEntities.PurchaseOrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseOrderJpaRepository extends JpaRepository<PurchaseOrderJpaEntity, UUID> {
}
