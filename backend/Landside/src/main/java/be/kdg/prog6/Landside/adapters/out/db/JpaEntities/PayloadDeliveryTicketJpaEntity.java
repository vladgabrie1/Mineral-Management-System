package be.kdg.prog6.Landside.adapters.out.db.JpaEntities;

import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payload_delivery_tickets", catalog = "land")
public class PayloadDeliveryTicketJpaEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "weight")
    private Integer weight;

    @Column(nullable = false, name = "license_plate")
    private String licensePlate;

    @Column(nullable = false, name = "delivery_time")
    private LocalDateTime deliveryTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name="material_type")
    private MaterialType materialType;

    @Column(nullable = false, name = "warehouse_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID warehouseId;

    @OneToOne
    @JoinColumn(name = "truck_id", referencedColumnName = "id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private TruckJpaEntity truck;

    public PayloadDeliveryTicketJpaEntity(UUID id, Integer weight, String licensePlate, LocalDateTime deliveryTime, MaterialType materialType, UUID warehouseId, TruckJpaEntity truck) {
        this.id = id;
        this.weight = weight;
        this.licensePlate = licensePlate;
        this.deliveryTime = deliveryTime;
        this.materialType = materialType;
        this.warehouseId = warehouseId;
        this.truck = truck;
    }

    public PayloadDeliveryTicketJpaEntity() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public TruckJpaEntity getTruck() {
        return truck;
    }

    public void setTruck(TruckJpaEntity truck) {
        this.truck = truck;
    }
}
