package be.kdg.prog6.Landside.domain.WeighBridge;

import java.util.Objects;
import java.util.UUID;

public record WeighBridgeTransactionId(UUID id) {
    public WeighBridgeTransactionId {
        Objects.requireNonNull(id);
    }
}
