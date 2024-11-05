package be.kdg.prog6.Landside.adapters.in.web.dtos;

import java.time.LocalDateTime;

public class PdtDTO {
    private double weight;
    private String licensePlate;
    private LocalDateTime deliveryTime;
    private String materialType;
    private String warehouseId;
    private boolean onTime;
    private LocalDateTime truckArrivalTime;

    public PdtDTO(double weight, String licensePlate, LocalDateTime deliveryTime, String materialType, String warehouseId, boolean onTime, LocalDateTime truckArrivalTime) {
        this.weight = weight;
        this.licensePlate = licensePlate;
        this.deliveryTime = deliveryTime;
        this.materialType = materialType;
        this.warehouseId = warehouseId;
        this.onTime = onTime;
        this.truckArrivalTime = truckArrivalTime;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
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

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public boolean isOnTime() {
        return onTime;
    }

    public void setOnTime(boolean onTime) {
        this.onTime = onTime;
    }

    public LocalDateTime getTruckArrivalTime() {
        return truckArrivalTime;
    }

    public void setTruckArrivalTime(LocalDateTime truckArrivalTime) {
        this.truckArrivalTime = truckArrivalTime;
    }
}
