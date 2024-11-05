package be.kdg.prog6.Landside.adapters.out.db.JpaEntities;

import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(catalog = "land", name = "appointments")
public class AppointmentJpaEntity {
    @Id
    @Column(name = "id", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name ="seller_id", columnDefinition ="varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID sellerId;

    @Column(name = "license_plate")
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    @Column(name = "material_type")
    private MaterialType materialType;

    @Column(name = "start_time")
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private LocalDateTime endTime;

    @Column(name = "warehouse_id")
    private UUID warehouseId;

    public AppointmentJpaEntity(UUID id, UUID sellerId, String licensePlate, MaterialType materialType, LocalDateTime startTime, LocalDateTime endTime, UUID warehouseId) {
        this.id = id;
        this.sellerId = sellerId;
        this.licensePlate = licensePlate;
        this.materialType = materialType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.warehouseId = warehouseId;
    }

    public AppointmentJpaEntity() {
    }

    public AppointmentJpaEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }
}
