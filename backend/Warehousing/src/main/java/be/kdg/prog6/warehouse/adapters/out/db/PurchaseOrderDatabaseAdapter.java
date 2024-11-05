package be.kdg.prog6.warehouse.adapters.out.db;

import be.kdg.prog6.warehouse.adapters.out.db.JpaEntities.PurchaseOrderJpaEntity;
import be.kdg.prog6.warehouse.adapters.out.db.JpaEntities.PurchaseOrderLineJpaEntity;
import be.kdg.prog6.warehouse.adapters.out.db.Repositories.PurchaseOrderJpaRepository;
import be.kdg.prog6.warehouse.domain.*;
import be.kdg.prog6.warehouse.ports.out.LoadPurchaseOrderPort;
import be.kdg.prog6.warehouse.ports.out.SavePurchaseOrderPort;
import be.kdg.prog6.warehouse.ports.out.UpdatePurchaseOrderPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


@Component
public class PurchaseOrderDatabaseAdapter implements LoadPurchaseOrderPort, SavePurchaseOrderPort, UpdatePurchaseOrderPort {
    private final PurchaseOrderJpaRepository purchaseOrderJpaRepository;

    public PurchaseOrderDatabaseAdapter(PurchaseOrderJpaRepository purchaseOrderJpaRepository) {
        this.purchaseOrderJpaRepository = purchaseOrderJpaRepository;
    }

    @Override
    public Optional<PurchaseOrder> findPurchaseOrderById(UUID purchaseOrderId) {
        return Optional.empty();
    }

    @Override
    public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder) {
        PurchaseOrderJpaEntity entity = mapToEntity(purchaseOrder);
        PurchaseOrderJpaEntity savedEntity = purchaseOrderJpaRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public void updatePurchaseOrder(PurchaseOrder purchaseOrder) {
        PurchaseOrderJpaEntity entity = mapToEntity(purchaseOrder);
        purchaseOrderJpaRepository.save(entity);
    }

    private PurchaseOrderJpaEntity mapToEntity(PurchaseOrder purchaseOrder) {
        PurchaseOrderJpaEntity entity = new PurchaseOrderJpaEntity(
                purchaseOrder.getPurchaseOrderNumber().purchaseOrderNumber(),
                purchaseOrder.getDate(),
                purchaseOrder.getCustomer().customerNumber().customerNumber(),
                purchaseOrder.getCustomer().name(),
                purchaseOrder.isShipped()
        );


        for (PurchaseOrderLine line : purchaseOrder.getPurchaseOrderLines()) {
            PurchaseOrderLineJpaEntity lineEntity = new PurchaseOrderLineJpaEntity(
                    entity,
                    line.getWeightInTons().value(),
                    line.getRawMaterial()
            );
            entity.addPurchaseOrderLine(lineEntity);
        }

        return entity;
    }

    private PurchaseOrder mapToDomain(PurchaseOrderJpaEntity entity) {
        return new PurchaseOrder(
                new PurchaseOrder.PurchaseOrderNumber(entity.getPurchaseOrderNumber()),
                entity.getDate(),
                new Customer(new CustomerNumber(entity.getCustomerNumber()), entity.getCustomerName()),
                entity.getPurchaseOrderLines().stream()
                        .map(line -> new PurchaseOrderLine(new WeightInTons(line.getWeightInTons()), line.getRawMaterial()))
                        .toList(),
                entity.isShipped()
        );
    }
}
