package be.kdg.prog6.Landside.adapters.out.db;

import be.kdg.prog6.Landside.adapters.out.db.JpaEntities.AppointmentJpaEntity;
import be.kdg.prog6.Landside.adapters.out.db.JpaRepositories.AppointmentJpaRepository;
import be.kdg.prog6.Landside.domain.*;
import be.kdg.prog6.Landside.ports.out.LoadSchedulePort;
import be.kdg.prog6.Landside.ports.out.UpdateSchedulePort;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ScheduleDatabaseAdapter implements LoadSchedulePort, UpdateSchedulePort {
    private final AppointmentJpaRepository appointmentJpaRepository;

    public ScheduleDatabaseAdapter(AppointmentJpaRepository appointmentJpaRepository) {
        this.appointmentJpaRepository = appointmentJpaRepository;
    }


    @Override
    public void scheduleUpdated(HourlySchedule schedule) {
        List<Appointment> appointments = schedule.getAppointments();

        if (appointments.isEmpty()) {
            return;
        }

        Appointment newAppointment = appointments.get(appointments.size() - 1);
        appointmentJpaRepository.save(toAppointmentJpaEntity(newAppointment));
    }


    @Override
    public HourlySchedule loadScheduleForHour(LocalDateTime dateTime) {
        LocalDateTime startTime = dateTime.truncatedTo(ChronoUnit.HOURS);
        LocalDateTime endTime = startTime.plusHours(1);

        List<AppointmentJpaEntity> jpaAppointments = appointmentJpaRepository.findByStartTimeBetween(startTime, endTime);

        List<Appointment> appointments = jpaAppointments.stream()
                .map(this::toAppointment)
                .collect(Collectors.toList());

        return new HourlySchedule(appointments);
    }

    private Appointment toAppointment(final AppointmentJpaEntity appointmentJpaEntity) {
        final Appointment.AppointmentId id = new Appointment.AppointmentId(appointmentJpaEntity.getId());
        final SellerId sellerId = new SellerId(appointmentJpaEntity.getSellerId());
        final LicensePlate licensePlate = new LicensePlate(appointmentJpaEntity.getLicensePlate());
        final AppointmentWindow appointmentWindow = new AppointmentWindow(appointmentJpaEntity.getStartTime(), appointmentJpaEntity.getEndTime());
        final WarehouseId warehouseId = new WarehouseId(appointmentJpaEntity.getWarehouseId());

        return new Appointment(id, sellerId, licensePlate, appointmentJpaEntity.getMaterialType(), appointmentWindow,warehouseId );
    }

    private AppointmentJpaEntity toAppointmentJpaEntity(Appointment appointment) {
        return new AppointmentJpaEntity(
                appointment.id().id(),
                appointment.sellerId().id(),
                appointment.licensePlate().licensePlate(),
                appointment.materialType(),
                appointment.appointmentWindow().getStartTime(),
                appointment.appointmentWindow().getEndTime(),
                appointment.warehouseId().id()
        );
    }

}
