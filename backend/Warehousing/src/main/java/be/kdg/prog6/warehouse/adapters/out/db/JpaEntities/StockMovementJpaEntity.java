package be.kdg.prog6.warehouse.adapters.out.db.JpaEntities;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.warehouse.domain.MovementType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "stock_movements", catalog = "warehousing")
public class StockMovementJpaEntity {
    @Id
    @Column(name = "id")
    @JdbcTypeCode(Types.VARCHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "warehouse_id", nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID warehouseId;

    @Column(name = "amount")
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "material_type")
    private MaterialType materialType;

    @Column(nullable = false, name = "timestamp")
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "movement_type")
    private MovementType movementType;

    public StockMovementJpaEntity() {
    }

    public StockMovementJpaEntity(UUID id, UUID warehouseId, double amount, MovementType movementType, LocalDateTime timestamp, MaterialType materialType) {
        this.id = id;
        this.warehouseId = warehouseId;
        this.amount = amount;
        this.movementType = movementType;
        this.timestamp = timestamp;
        this.materialType = materialType;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public double getAmount() {
        return amount;
    }
}