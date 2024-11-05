package be.kdg.prog6.Landside.adapters.in.web.dtos;

import be.kdg.prog6.common.domain.MaterialType;

import java.time.LocalDateTime;
import java.util.UUID;

public class PayloadDeliveryTicketDto {
    private UUID id;
    private String licensePlate;
    private LocalDateTime deliveryTime;
    private MaterialType materialType;
    private UUID warehouseNumber;

    public PayloadDeliveryTicketDto(UUID id, String licensePlate, LocalDateTime deliveryTime, MaterialType materialType, UUID warehouseNumber) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.deliveryTime = deliveryTime;
        this.materialType = materialType;
        this.warehouseNumber = warehouseNumber;
    }

    public PayloadDeliveryTicketDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public UUID getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(UUID warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }
}
