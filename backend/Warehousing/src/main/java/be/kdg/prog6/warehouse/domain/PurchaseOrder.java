package be.kdg.prog6.warehouse.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PurchaseOrder {
    private PurchaseOrderNumber purchaseOrderNumber;
    private LocalDate date;
    private Customer customer;
    private List<PurchaseOrderLine> purchaseOrderLines;
    private boolean isShipped;

    public record PurchaseOrderNumber(UUID purchaseOrderNumber) { }

    public PurchaseOrder(PurchaseOrderNumber purchaseOrderNumber, LocalDate date, Customer customer, List<PurchaseOrderLine> purchaseOrderLines, boolean isShipped) {
        this.purchaseOrderNumber = purchaseOrderNumber;
        this.date = date;
        this.customer = customer;
        this.purchaseOrderLines = purchaseOrderLines;
        this.isShipped = isShipped;
    }

    public PurchaseOrderNumber getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<PurchaseOrderLine> getPurchaseOrderLines() {
        return purchaseOrderLines;
    }

    public boolean isShipped() {
        return isShipped;
    }


}
