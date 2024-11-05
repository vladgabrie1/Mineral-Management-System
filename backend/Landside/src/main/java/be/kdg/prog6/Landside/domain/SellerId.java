package be.kdg.prog6.Landside.domain;

import java.util.Objects;
import java.util.UUID;

public record SellerId(UUID id) {
    public SellerId {
        Objects.requireNonNull(id);
    }
}
