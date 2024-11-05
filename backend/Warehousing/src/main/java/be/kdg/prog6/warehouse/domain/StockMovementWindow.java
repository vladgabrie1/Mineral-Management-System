package be.kdg.prog6.warehouse.domain;

import java.util.ArrayList;
import java.util.List;

public class StockMovementWindow {
    private final List<StockMovement> stockMovements = new ArrayList<>();

    public WeightInTons calculateStock() {
        return stockMovements.stream()
                .map(movement -> movement.movementType() == MovementType.INBOUND ? movement.amount() : movement.amount().negate())
                .reduce(WeightInTons.of(0), WeightInTons::add);
    }

    public List<StockMovement> getStockMovements() {
        return stockMovements;
    }

    public boolean add(StockMovement stockMovement) {
        return stockMovements.add(stockMovement);
    }
}
