package be.kdg.prog6.Landside.ports.out;

import be.kdg.prog6.Landside.domain.WeighBridge.WeighingBridge;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighingBridgeNumber;

public interface LoadWeighingBridgePort {
    WeighingBridge getAvailableWeighingBridge();
    WeighingBridge getWeighingBridgeByNumber(WeighingBridgeNumber number);
}
