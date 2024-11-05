package be.kdg.prog6.Landside.domain;

import java.util.Objects;
import java.util.UUID;

public record WarehouseId(UUID id) {
    public WarehouseId{
        Objects.requireNonNull(id);
    }
}
