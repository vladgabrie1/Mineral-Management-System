package be.kdg.prog6.Landside.ports.out;

import be.kdg.prog6.Landside.domain.WeighBridge.WeighBridgeTransaction;

@FunctionalInterface
public interface SaveWeighBridgeTransactionPort {
    void saveWBT(WeighBridgeTransaction wbt);
}
