package be.kdg.prog6.Landside.core;

import be.kdg.prog6.Landside.TestIds;
import be.kdg.prog6.Landside.domain.*;
import be.kdg.prog6.Landside.ports.out.LoadSchedulePort;
import be.kdg.prog6.common.domain.MaterialType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoadSchedulePortStub implements LoadSchedulePort {
    @Override
    public HourlySchedule loadScheduleForHour(LocalDateTime dateTime) {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(
                new Appointment.AppointmentId(UUID.randomUUID()),
                TestIds.SELLER_ID,
                new LicensePlate("ABC123"),
                MaterialType.SLAG,
                new AppointmentWindow(dateTime),
                new WarehouseId(UUID.randomUUID())
        ));

        return new HourlySchedule(appointments);
    }
}
