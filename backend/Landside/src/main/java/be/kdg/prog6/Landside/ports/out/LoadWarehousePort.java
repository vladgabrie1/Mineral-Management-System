package be.kdg.prog6.Landside.ports.out;

import be.kdg.prog6.Landside.domain.Warehouse;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.Landside.domain.SellerId;

@FunctionalInterface
public interface LoadWarehousePort {
    Warehouse loadWarehouseBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType);
}
