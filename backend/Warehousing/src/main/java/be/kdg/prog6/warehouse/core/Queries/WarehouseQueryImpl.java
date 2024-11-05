package be.kdg.prog6.warehouse.core.Queries;

import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.ports.in.WarehouseQuery;
import be.kdg.prog6.warehouse.ports.out.LoadWarehousePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseQueryImpl implements WarehouseQuery {
    private final LoadWarehousePort loadWarehousePort;

    public WarehouseQueryImpl(LoadWarehousePort loadWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    public List<Warehouse> findAllWarehouses() {
        return loadWarehousePort.findAll();
    }
}
