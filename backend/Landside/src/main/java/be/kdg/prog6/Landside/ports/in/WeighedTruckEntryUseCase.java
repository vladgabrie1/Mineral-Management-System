package be.kdg.prog6.Landside.ports.in;

import be.kdg.prog6.Landside.domain.WarehouseId;
import be.kdg.prog6.Landside.ports.in.commands.WeighTruckCommand;

@FunctionalInterface
public interface WeighedTruckEntryUseCase {
    WarehouseId truckHasBeenWeighted(WeighTruckCommand weighTruckCommand);
}