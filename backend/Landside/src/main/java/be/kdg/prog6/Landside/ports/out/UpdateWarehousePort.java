package be.kdg.prog6.Landside.ports.out;

import be.kdg.prog6.Landside.domain.Warehouse;

@FunctionalInterface
public interface UpdateWarehousePort {
    void updateWarehouse(Warehouse warehouse);
}
