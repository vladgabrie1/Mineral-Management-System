package be.kdg.prog6.warehouse.ports.out;

import be.kdg.prog6.warehouse.domain.PurchaseOrder;

@FunctionalInterface
public interface UpdatePurchaseOrderPort {
    void updatePurchaseOrder(PurchaseOrder purchaseOrder);
}
