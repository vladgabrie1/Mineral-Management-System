package be.kdg.prog6.Landside.core;

import be.kdg.prog6.Landside.domain.*;
import be.kdg.prog6.Landside.exceptions.TruckException;
import be.kdg.prog6.Landside.ports.in.DockTruckUseCase;
import be.kdg.prog6.Landside.ports.in.commands.DockTruckCommand;
import be.kdg.prog6.Landside.ports.out.LoadSchedulePort;
import be.kdg.prog6.Landside.ports.out.LoadTruckPort;
import be.kdg.prog6.Landside.ports.out.SavePayloadDeliveryTicketPort;
import be.kdg.prog6.Landside.ports.out.UpdateTruckPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.UUID;

@Service
public class DockTruckUseCaseImpl implements DockTruckUseCase {
    private final LoadSchedulePort loadSchedulePort;
    private final SavePayloadDeliveryTicketPort savePayloadDeliveryTicketPort;
    private final LoadTruckPort loadTruckPort;
    private final UpdateTruckPort updateTruckPort;

    public DockTruckUseCaseImpl(LoadSchedulePort loadSchedulePort, SavePayloadDeliveryTicketPort savePayloadDeliveryTicketPort, LoadTruckPort loadTruckPort, UpdateTruckPort updateTruckPort) {
        this.loadSchedulePort = loadSchedulePort;
        this.savePayloadDeliveryTicketPort = savePayloadDeliveryTicketPort;
        this.loadTruckPort = loadTruckPort;
        this.updateTruckPort = updateTruckPort;
    }

    @Override
    @Transactional
    public PayloadDeliveryTicket dockTruck(DockTruckCommand command) {
        Appointment appointment = loadSchedulePort.loadScheduleForHour(command.dockTime()).getAppointmentByLicensePlate(command.licensePlate());
        final Truck truck = loadTruckPort.loadOnSiteTruckByLicensePlate(command.licensePlate()).orElseThrow(() -> new TruckException("Truck not found"));
        truck.setLocation(TruckLocation.DOCK);

        /*
        When the truck is docked, an incomplete Payload Delivery Ticket is created
        This will be completed and returned when the truck is weighed and leaves the facility
         */
        PayloadDeliveryTicket pdt = new PayloadDeliveryTicket(
                new PayloadDeliveryTicketId(UUID.randomUUID()),
                command.licensePlate(),
                command.dockTime(),
                appointment.materialType(),
                appointment.warehouseId(),
                truck
        );

        updateTruckPort.updateTruck(truck);
        savePayloadDeliveryTicketPort.savePayloadDeliveryTicket(pdt);
        return pdt;
    }
}
