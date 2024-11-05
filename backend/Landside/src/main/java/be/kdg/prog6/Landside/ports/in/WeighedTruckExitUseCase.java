package be.kdg.prog6.Landside.ports.in;

import be.kdg.prog6.Landside.domain.WeighBridge.WeighBridgeTransaction;
import be.kdg.prog6.Landside.ports.in.commands.WeighTruckCommand;

@FunctionalInterface
public interface WeighedTruckExitUseCase {
    WeighBridgeTransaction truckHasBeenWeighed(WeighTruckCommand command);
}
