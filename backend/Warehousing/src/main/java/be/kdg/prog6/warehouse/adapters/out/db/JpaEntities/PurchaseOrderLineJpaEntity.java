package be.kdg.prog6.warehouse.adapters.out.db.JpaEntities;

import be.kdg.prog6.warehouse.domain.RawMaterial;
import be.kdg.prog6.warehouse.domain.WeightInTons;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(catalog = "warehousing", name = "purchase_order_lines")
public class PurchaseOrderLineJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "purchase_order_number", nullable = false)
    private PurchaseOrderJpaEntity purchaseOrder;

    @Column(name="weight")
    private double weightInTons;

    @Enumerated(EnumType.STRING)
    @Column(name = "raw_material", nullable = false)
    private RawMaterial rawMaterial;

    public PurchaseOrderLineJpaEntity() {

    }

    public void setPurchaseOrder(PurchaseOrderJpaEntity purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public PurchaseOrderLineJpaEntity(PurchaseOrderJpaEntity purchaseOrder, double weightInTons, RawMaterial rawMaterial) {
        this.purchaseOrder = purchaseOrder;
        this.weightInTons = weightInTons;
        this.rawMaterial = rawMaterial;
    }

    public UUID getId() {
        return id;
    }

    public PurchaseOrderJpaEntity getPurchaseOrder() {
        return purchaseOrder;
    }

    public double getWeightInTons() {
        return weightInTons;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }
}
