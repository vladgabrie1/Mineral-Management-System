package be.kdg.prog6.Landside.adapters.out.db;

import be.kdg.prog6.Landside.adapters.out.db.JpaEntities.WarehouseLandsideJpaEntity;
import be.kdg.prog6.Landside.adapters.out.db.JpaRepositories.WarehouseLandsideJpaRepository;
import be.kdg.prog6.Landside.domain.Warehouse;
import be.kdg.prog6.Landside.ports.out.LoadWarehousePort;
import be.kdg.prog6.Landside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.Landside.domain.SellerId;
import be.kdg.prog6.Landside.domain.WarehouseId;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class WarehouseLandsideDatabaseAdapter implements LoadWarehousePort, UpdateWarehousePort {

    private final WarehouseLandsideJpaRepository jpaRepository;

    public WarehouseLandsideDatabaseAdapter(WarehouseLandsideJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }


    @Override
    public void updateWarehouse(Warehouse warehouse) {
        final WarehouseLandsideJpaEntity jpaEntity = jpaRepository.findById(warehouse.getId().id())
                .orElseGet(() -> new WarehouseLandsideJpaEntity(warehouse.getId().id(),0, warehouse.getSellerId().id(),warehouse.getMaterialType()));

        jpaEntity.setFillPercentage(warehouse.getFillPercentage());
        jpaRepository.save(jpaEntity);

    }

    @Override
    public Warehouse loadWarehouseBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType) {
        return jpaRepository.findBySellerIdAndMaterialType(sellerId.id(), materialType)
                .map(wh -> new Warehouse(new WarehouseId(wh.getId()), wh.getFillPercentage(), new SellerId(wh.getSellerId()), wh.getMaterialType()))
                .orElse(null);
    }
}
