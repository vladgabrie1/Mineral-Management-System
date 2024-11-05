package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.warehouse.core.exceptions.WarehouseException;
import be.kdg.prog6.warehouse.domain.StockMovement;
import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.ports.in.ReceiveRawMaterialCommand;
import be.kdg.prog6.warehouse.ports.in.ReceiveRawMaterialUseCase;
import be.kdg.prog6.warehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.warehouse.ports.out.SaveWarehousePort;
import be.kdg.prog6.warehouse.ports.out.StockMovementCreatedPort;
import be.kdg.prog6.warehouse.ports.out.UpdateWarehousePort;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiveRawMaterialUseCaseImpl implements ReceiveRawMaterialUseCase {
    private static final Logger LOGGER = LogManager.getLogger(ReceiveRawMaterialUseCaseImpl.class);
    private final LoadWarehousePort loadWarehousePort;
    private final List<UpdateWarehousePort> updateWarehousePorts;
    private final StockMovementCreatedPort stockMovementCreatedPort;
    private final SaveWarehousePort saveWarehousePort;

    public ReceiveRawMaterialUseCaseImpl(LoadWarehousePort loadWarehousePort, List<UpdateWarehousePort> updateWarehousePorts, StockMovementCreatedPort stockMovementCreatedPort, SaveWarehousePort saveWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
        this.updateWarehousePorts = updateWarehousePorts;
        this.stockMovementCreatedPort = stockMovementCreatedPort;
        this.saveWarehousePort = saveWarehousePort;
    }

    @Override
    @Transactional
    public void receiveRawMaterial(ReceiveRawMaterialCommand command) {
        Warehouse warehouse = loadWarehousePort.loadWarehouseById(command.warehouseId()).orElseThrow(
                () -> new WarehouseException("Warehouse with id %s not found".formatted(command.warehouseId()))
        );

        final StockMovement stockMovement = warehouse.addStock(command.amount(), command.materialType(), command.deliveryTime());
        stockMovementCreatedPort.stockMovementCreated(stockMovement, warehouse.getId());

        if(warehouse.shouldCreateSnapshot()){
            warehouse.createSnapshot();
            saveWarehousePort.saveWarehouse(warehouse);
        }
        updateWarehousePorts.forEach(port -> port.updateWarehouse(warehouse));
        LOGGER.info("Stock movement created: {}", stockMovement);

    }
}
