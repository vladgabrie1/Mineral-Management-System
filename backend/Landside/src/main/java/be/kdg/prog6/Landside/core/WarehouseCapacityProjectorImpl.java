package be.kdg.prog6.Landside.core;

import be.kdg.prog6.Landside.domain.Warehouse;
import be.kdg.prog6.Landside.ports.in.WarehouseCapacityProjector;
import be.kdg.prog6.Landside.ports.out.LoadWarehousePort;
import be.kdg.prog6.Landside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.Landside.domain.SellerId;
import be.kdg.prog6.Landside.domain.WarehouseId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class WarehouseCapacityProjectorImpl implements WarehouseCapacityProjector {
    private final LoadWarehousePort loadWarehousePort;
    private final UpdateWarehousePort updateWarehousePort;

    public WarehouseCapacityProjectorImpl(LoadWarehousePort loadWarehousePort, UpdateWarehousePort updateWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
        this.updateWarehousePort = updateWarehousePort;
    }

    @Transactional
    @Override
    public void projectCapacity(WarehouseId warehouseId, double warehouseFillPercentage, SellerId sellerId, MaterialType materialType) {
        Warehouse warehouse = loadWarehousePort.loadWarehouseBySellerIdAndMaterialType(sellerId, materialType);
        if (warehouse == null) {
            warehouse = new Warehouse(warehouseId, warehouseFillPercentage, sellerId, materialType);
        }
        warehouse.setFillPercentage(warehouseFillPercentage);
        updateWarehousePort.updateWarehouse(warehouse);
    }
}
