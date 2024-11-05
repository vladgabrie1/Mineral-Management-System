package be.kdg.prog6.Landside.domain;

import be.kdg.prog6.common.domain.MaterialType;

import java.time.LocalDateTime;

public class PayloadDeliveryTicket {
    private final PayloadDeliveryTicketId id;
    private WeightInTons weight;
    private final LicensePlate licensePlate;
    private final LocalDateTime deliveryTime;
    private final MaterialType materialType;
    private final WarehouseId warehouseId;
    private final Truck truck;


    public PayloadDeliveryTicket(PayloadDeliveryTicketId id, LicensePlate licensePlate, LocalDateTime deliveryTime, MaterialType materialType, WarehouseId warehouseId, WeightInTons weight, Truck truck) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.deliveryTime = deliveryTime;
        this.materialType = materialType;
        this.warehouseId = warehouseId;
        this.weight = weight;
        this.truck = truck;
    }

    public PayloadDeliveryTicket(PayloadDeliveryTicketId id, LicensePlate licensePlate, LocalDateTime deliveryTime, MaterialType materialType, WarehouseId warehouseId, Truck truck) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.deliveryTime = deliveryTime;
        this.materialType = materialType;
        this.warehouseId = warehouseId;
        this.truck = truck;
    }

    public Truck getTruck() {
        return truck;
    }

    public PayloadDeliveryTicketId getId() {
        return id;
    }

    public WeightInTons getWeight() {
        return weight;
    }

    public void setWeight(WeightInTons weight) {
        this.weight = weight;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public WarehouseId getWarehouseId() {
        return warehouseId;
    }
}
