package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.MaterialType;

public class PurchaseOrderLine {
    private PurchaseOrder.PurchaseOrderNumber purchaseOrderNumber;
    private WeightInTons weightInTons;
    private RawMaterial rawMaterial;

    public PurchaseOrderLine(WeightInTons weightInTons, RawMaterial rawMaterial) {
        this.weightInTons = weightInTons;
        this.rawMaterial = rawMaterial;
    }

    public WeightInTons getWeightInTons() {
        return weightInTons;
    }

    public void setWeightInTons(WeightInTons weightInTons) {
        this.weightInTons = weightInTons;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public PurchaseOrder.PurchaseOrderNumber getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(PurchaseOrder.PurchaseOrderNumber purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }
}
