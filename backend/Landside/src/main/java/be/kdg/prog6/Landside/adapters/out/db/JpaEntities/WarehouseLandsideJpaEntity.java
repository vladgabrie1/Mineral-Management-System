package be.kdg.prog6.Landside.adapters.out.db.JpaEntities;

import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(catalog = "land", name = "landside_warehouses")
public class WarehouseLandsideJpaEntity {
    @Id
    @Column(name = "id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "fill_percentage")
    private double fillPercentage;

    @Column(name = "seller_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID sellerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "material_type")
    private MaterialType materialType;

    public WarehouseLandsideJpaEntity(UUID id, int fillPercentage, UUID sellerId, MaterialType materialType) {
        this.id = id;
        this.fillPercentage = fillPercentage;
        this.sellerId = sellerId;
        this.materialType = materialType;
    }

    public WarehouseLandsideJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getFillPercentage() {
        return fillPercentage;
    }

    public void setFillPercentage(double fillPercentage) {
        this.fillPercentage = fillPercentage;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }
}
