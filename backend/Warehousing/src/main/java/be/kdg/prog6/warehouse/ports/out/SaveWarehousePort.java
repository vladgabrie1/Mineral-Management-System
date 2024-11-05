package be.kdg.prog6.warehouse.ports.out;

import be.kdg.prog6.warehouse.domain.Warehouse;

@FunctionalInterface
public interface SaveWarehousePort {
    public void saveWarehouse(Warehouse warehouse);
}
