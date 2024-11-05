package be.kdg.prog6.warehouse.adapters.out.db.JpaEntities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(catalog = "warehousing" ,name = "purchase_orders")
public class PurchaseOrderJpaEntity {

    @Id
    @Column(name = "purchase_order_number", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID purchaseOrderNumber;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "customer_number")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID customerNumber;

    @Column(name = "customer_name")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String customerName;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseOrderLineJpaEntity> purchaseOrderLines = new ArrayList<>();
    ;

    @Column(name = "is_shipped", nullable = false)
    private boolean isShipped;

    public PurchaseOrderJpaEntity() {
    }


    public PurchaseOrderJpaEntity(UUID purchaseOrderNumber, LocalDate date, UUID customerNumber, String customerName, boolean isShipped) {
        this.purchaseOrderNumber = purchaseOrderNumber;
        this.date = date;
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.isShipped = isShipped;
    }

    public UUID getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(UUID purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
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

    public List<PurchaseOrderLineJpaEntity> getPurchaseOrderLines() {
        return purchaseOrderLines;
    }

    public void setPurchaseOrderLines(List<PurchaseOrderLineJpaEntity> purchaseOrderLines) {
        this.purchaseOrderLines = purchaseOrderLines;
    }

    public boolean isShipped() {
        return isShipped;
    }

    public void setShipped(boolean shipped) {
        isShipped = shipped;
    }

    public void addPurchaseOrderLine(PurchaseOrderLineJpaEntity line) {
        purchaseOrderLines.add(line);
        line.setPurchaseOrder(this);
    }
}
