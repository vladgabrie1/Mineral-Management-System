package be.kdg.prog6.Landside.core;

import be.kdg.prog6.Landside.exceptions.WarehouseException;
import be.kdg.prog6.Landside.domain.Appointment;
import be.kdg.prog6.Landside.domain.HourlySchedule;
import be.kdg.prog6.Landside.domain.Warehouse;
import be.kdg.prog6.Landside.ports.in.commands.MakeAppointmentCommand;
import be.kdg.prog6.Landside.ports.in.MakeAppointmentUseCase;
import be.kdg.prog6.Landside.ports.out.LoadSchedulePort;
import be.kdg.prog6.Landside.ports.out.LoadWarehousePort;
import be.kdg.prog6.Landside.ports.out.UpdateSchedulePort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MakeAppointmentUseCaseImpl implements MakeAppointmentUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(MakeAppointmentUseCaseImpl.class);

    private final List<UpdateSchedulePort> scheduleUpdatePorts;
    private final LoadSchedulePort loadSchedulePort;
    private final LoadWarehousePort loadWarehousePort;

    public MakeAppointmentUseCaseImpl(List<UpdateSchedulePort> scheduleUpdatePorts, LoadSchedulePort loadSchedulePort, LoadWarehousePort loadWarehousePort) {
        this.scheduleUpdatePorts = scheduleUpdatePorts;
        this.loadSchedulePort = loadSchedulePort;
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    @Transactional
    public Appointment makeAppointment(MakeAppointmentCommand makeAppointmentCommand) {

        Warehouse warehouse = loadWarehousePort.loadWarehouseBySellerIdAndMaterialType(makeAppointmentCommand.sellerId(), makeAppointmentCommand.materialType());


        if (warehouse.isFull()) {
            throw new WarehouseException("Warehouse is full right now!");
        }

        HourlySchedule hourlySchedule = loadSchedulePort.loadScheduleForHour(makeAppointmentCommand.time());
        Appointment newAppointment = hourlySchedule.addAppointment(
                makeAppointmentCommand.sellerId(),
                makeAppointmentCommand.time(),
                makeAppointmentCommand.licensePlate(),
                makeAppointmentCommand.materialType(),
                warehouse.getId()

        );

        scheduleUpdatePorts.forEach(port -> port.scheduleUpdated(hourlySchedule));
        return newAppointment;
    }
}
