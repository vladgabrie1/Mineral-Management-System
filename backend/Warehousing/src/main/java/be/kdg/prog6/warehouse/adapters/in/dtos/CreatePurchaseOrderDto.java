package be.kdg.prog6.warehouse.adapters.in.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CreatePurchaseOrderDto {
    private LocalDate date;
    private UUID customerNumber;
    private String customerName;
    private List<PurchaseOrderLineDto> purchaseOrderLines;


    public static class PurchaseOrderLineDto {
        private double weightInTons;
        private String rawMaterial;


        public double getWeightInTons() {
            return weightInTons;
        }

        public void setWeightInTons(double weightInTons) {
            this.weightInTons = weightInTons;
        }

        public String getRawMaterial() {
            return rawMaterial;
        }

        public void setRawMaterial(String rawMaterial) {
            this.rawMaterial = rawMaterial;
        }
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UUID getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(UUID customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<PurchaseOrderLineDto> getPurchaseOrderLines() {
        return purchaseOrderLines;
    }

    public void setPurchaseOrderLines(List<PurchaseOrderLineDto> purchaseOrderLines) {
        this.purchaseOrderLines = purchaseOrderLines;
    }
}
