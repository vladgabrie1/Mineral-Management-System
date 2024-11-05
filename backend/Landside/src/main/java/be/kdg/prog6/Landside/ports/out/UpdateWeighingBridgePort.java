package be.kdg.prog6.Landside.ports.out;

import be.kdg.prog6.Landside.domain.WeighBridge.WeighingBridge;

public interface UpdateWeighingBridgePort {
    void weighingBridgeAvailabilityChanged(WeighingBridge weighingBridge);
}
