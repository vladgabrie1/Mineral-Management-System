package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.warehouse.domain.PurchaseOrder;
import be.kdg.prog6.warehouse.ports.in.CreatePurchaseOrderCommand;
import be.kdg.prog6.warehouse.ports.in.CreatePurchaseOrderUseCase;
import be.kdg.prog6.warehouse.ports.out.SavePurchaseOrderPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreatePurchaseOrderUseCaseImpl implements CreatePurchaseOrderUseCase {
    private final SavePurchaseOrderPort savePurchaseOrderPort;

    public CreatePurchaseOrderUseCaseImpl(SavePurchaseOrderPort savePurchaseOrderPort) {
        this.savePurchaseOrderPort = savePurchaseOrderPort;
    }

    @Override
    public void createPurchaseOrder(CreatePurchaseOrderCommand command) {
        PurchaseOrder purchaseOrder = new PurchaseOrder(
                new PurchaseOrder.PurchaseOrderNumber(UUID.randomUUID()),
                command.date(),
                command.customer(),
                command.purchaseOrderLines(),
                false
        );
        savePurchaseOrderPort.savePurchaseOrder(purchaseOrder);
    }
}
