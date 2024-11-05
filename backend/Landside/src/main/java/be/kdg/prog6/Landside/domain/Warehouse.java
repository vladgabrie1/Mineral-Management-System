package be.kdg.prog6.Landside.domain;

import be.kdg.prog6.common.domain.MaterialType;

public class Warehouse {
    private final WarehouseId id;
    private double fillPercentage;
    private SellerId sellerId;
    private MaterialType materialType;

    private static final double MAX_FILL_PERCENTAGE = 80;

    public Warehouse(WarehouseId id, double fillPercentage, SellerId sellerId, MaterialType materialType) {
        this.id = id;
        this.fillPercentage = fillPercentage;
        this.sellerId = sellerId;
        this.materialType = materialType;
    }

    public Warehouse(WarehouseId warehouseId, double fillPercentage) {
        this.id = warehouseId;
        this.fillPercentage = fillPercentage;
    }

    public boolean isFull(){
        return this.fillPercentage > MAX_FILL_PERCENTAGE;
    }

    public WarehouseId getId() {
        return id;
    }

    public double getFillPercentage() {
        return fillPercentage;
    }

    public void setFillPercentage(double fillPercentage) {
        this.fillPercentage = fillPercentage;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public void setSellerId(SellerId sellerId) {
        this.sellerId = sellerId;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }
}
