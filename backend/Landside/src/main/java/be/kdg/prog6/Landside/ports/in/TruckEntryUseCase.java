package be.kdg.prog6.Landside.ports.in;

import be.kdg.prog6.Landside.core.TruckEntryResult;
import be.kdg.prog6.Landside.ports.in.commands.TruckEntryCommand;

@FunctionalInterface
public interface TruckEntryUseCase {
    TruckEntryResult truckHasEntered(TruckEntryCommand command);
}
