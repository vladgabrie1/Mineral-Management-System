package be.kdg.prog6.warehouse.ports.out;

import be.kdg.prog6.warehouse.domain.PurchaseOrder;

import java.util.Optional;
import java.util.UUID;

public interface LoadPurchaseOrderPort {
    Optional<PurchaseOrder> findPurchaseOrderById(UUID purchaseOrderId);
}
