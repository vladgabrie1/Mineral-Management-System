package be.kdg.prog6.Landside.ports.in;

import be.kdg.prog6.Landside.domain.PayloadDeliveryTicket;
import be.kdg.prog6.Landside.ports.in.commands.DockTruckCommand;

@FunctionalInterface
public interface DockTruckUseCase {
    public PayloadDeliveryTicket dockTruck(DockTruckCommand command);
}
