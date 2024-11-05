package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.MaterialType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Warehouse {
    private  String name;
    private final WarehouseId id;
    private final SellerId sellerId;
    private MaterialType materialType;
    private final StockMovementWindow stockMovementWindow;
    private final WeightInTons baseStock;
    private  LocalDateTime baseStockTimestamp;

    private static final WeightInTons MAX_CAPACITY = WeightInTons.of(500);

    public record WarehouseId(UUID id) {
        public WarehouseId {
            Objects.requireNonNull(id);
        }
    }

    public Warehouse(String name, WarehouseId id, SellerId sellerId, MaterialType materialType, StockMovementWindow stockMovementWindow, WeightInTons baseStock, LocalDateTime baseStockTimestamp) {
        this.name = name;
        this.id = id;
        this.sellerId = sellerId;
        this.materialType = materialType;
        this.stockMovementWindow = stockMovementWindow;
        this.baseStock = baseStock;
        this.baseStockTimestamp = baseStockTimestamp;
    }

    public Warehouse(WarehouseId id, SellerId sellerId, MaterialType materialType, StockMovementWindow stockMovements) {
        this.id = id;
        this.sellerId = sellerId;
        this.materialType = materialType;
        this.stockMovementWindow = stockMovements;
        this.baseStock = WeightInTons.of(0);
        this.baseStockTimestamp = null;
    }

    public Warehouse(WarehouseId id, SellerId sellerId, MaterialType materialType, StockMovementWindow stockMovementWindow, WeightInTons baseStock, LocalDateTime baseStockTimestamp) {
        this.id = id;
        this.sellerId = sellerId;
        this.materialType = materialType;
        this.stockMovementWindow = stockMovementWindow;
        this.baseStock = baseStock;
        this.baseStockTimestamp = baseStockTimestamp;
    }

    public LocalDateTime getBaseStockTimestamp() {
        return baseStockTimestamp;
    }

    public StockMovement addStock(WeightInTons amount, MaterialType materialType, LocalDateTime timestamp) {
        StockMovement stockMovement = new StockMovement(amount, MovementType.INBOUND, timestamp, materialType);
        stockMovementWindow.add(stockMovement);
        return stockMovement;
    }

    public void addStockMovement(StockMovement stockMovement) {
        stockMovementWindow.add(stockMovement);
    }

    public WeightInTons getCurrentStock(){
        return baseStock.add(stockMovementWindow.calculateStock());
    }

    // Real number should be around 100
    public boolean shouldCreateSnapshot(){
        return stockMovementWindow.getStockMovements().size() >= 1;
    }

    public void createSnapshot(){
        baseStock.add(stockMovementWindow.calculateStock());
        baseStockTimestamp = LocalDateTime.now();
    }

    public double getFillPercentage(){
        return getCurrentStock().value() / MAX_CAPACITY.value() * 100;
    }

    public WeightInTons getBaseStock() {
        return baseStock.add(stockMovementWindow.calculateStock());
    }

    public WarehouseId getId() {
        return id;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public StockMovementWindow getStockMovementWindow() {
        return stockMovementWindow;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
