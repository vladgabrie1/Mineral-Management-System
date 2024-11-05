package be.kdg.prog6.warehouse.adapters.in;

import be.kdg.prog6.warehouse.adapters.in.dtos.WarehouseDto;
import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.ports.in.WarehouseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private static final Logger log = LoggerFactory.getLogger(WarehouseController.class);
    private final WarehouseQuery warehouseQuery;

    public WarehouseController(WarehouseQuery warehouseQuery) {
        this.warehouseQuery = warehouseQuery;
    }

    @GetMapping
    public ResponseEntity<List<WarehouseDto>> getAllWarehouses(){
        log.info("Request to get all warehouses");
        try {
            List<Warehouse> warehouses = warehouseQuery.findAllWarehouses();

            if (warehouses.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            List<WarehouseDto> warehouseDtos = warehouses.stream()
                    .map(this::convertToDto)
                    .toList();

            return ResponseEntity.ok(warehouseDtos);
        } catch (Exception e) {
            log.error("Error occurred while fetching warehouses: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    private WarehouseDto convertToDto(Warehouse warehouse) {
        return new WarehouseDto(
                warehouse.getName(),
                warehouse.getId().id().toString(),
                warehouse.getSellerId().id().toString(),
                warehouse.getMaterialType().toString(),
                warehouse.getFillPercentage(),
                warehouse.getCurrentStock().value()
        );
    }


}
