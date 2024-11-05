package be.kdg.prog6.warehouse.adapters.out.db.JpaEntities;

import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(catalog = "warehousing", name = "warehouses")
public class WarehouseJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "warehouseId")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID warehouseId;

    @Column(name = "name")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String name;

    @Column(name = "seller_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID sellerId;

    @Column(name = "material_type")
    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    @Column(name = "base_stock")
    private double baseStock;

    @Column(name = "base_stock_timestamp")
    private LocalDateTime baseStockTimestamp;

    public WarehouseJpaEntity() {
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public double getBaseStock() {
        return baseStock;
    }

    public LocalDateTime getBaseStockTimestamp() {
        return baseStockTimestamp;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public void setBaseStock(double baseStock) {
        this.baseStock = baseStock;
    }

    public void setBaseStockTimestamp(LocalDateTime baseStockTimestamp) {
        this.baseStockTimestamp = baseStockTimestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
