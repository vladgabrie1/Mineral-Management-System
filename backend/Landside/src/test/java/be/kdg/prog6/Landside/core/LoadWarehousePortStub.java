package be.kdg.prog6.Landside.core;

import be.kdg.prog6.Landside.domain.SellerId;
import be.kdg.prog6.Landside.domain.Warehouse;
import be.kdg.prog6.Landside.domain.WarehouseId;
import be.kdg.prog6.Landside.ports.out.LoadWarehousePort;
import be.kdg.prog6.common.domain.MaterialType;

import java.util.UUID;

public class LoadWarehousePortStub implements LoadWarehousePort {
    @Override
    public Warehouse loadWarehouseBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType) {
        if(materialType == MaterialType.CEMENT){
            return new Warehouse(new WarehouseId(UUID.randomUUID()), 100);
        } else {
            return new Warehouse(new WarehouseId(UUID.randomUUID()), 10);
        }
    }
}
