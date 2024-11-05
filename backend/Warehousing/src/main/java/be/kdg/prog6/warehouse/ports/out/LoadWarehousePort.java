package be.kdg.prog6.warehouse.ports.out;

import be.kdg.prog6.warehouse.domain.Warehouse;

import java.util.List;
import java.util.Optional;


public interface LoadWarehousePort {
    Optional<Warehouse> loadWarehouseById(Warehouse.WarehouseId warehouseId);
    List<Warehouse> findAll();
}
