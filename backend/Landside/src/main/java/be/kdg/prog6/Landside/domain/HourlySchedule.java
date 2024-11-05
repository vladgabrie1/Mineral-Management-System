package be.kdg.prog6.Landside.domain;

import be.kdg.prog6.Landside.domain.exceptions.MaxNumberOfAppointmentsException;
import be.kdg.prog6.common.domain.MaterialType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class HourlySchedule {
    private static final int MAX_APPOINTMENTS_PER_HOUR = 4;
    private static final Logger LOGGER = LoggerFactory.getLogger(HourlySchedule.class);

    private final List<Appointment> appointments;

    public HourlySchedule(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Appointment> getAppointments() {
        return Collections.unmodifiableList(appointments);
    }

    public Appointment addAppointment(SellerId sellerId, LocalDateTime date, LicensePlate licensePlate, MaterialType materialType, WarehouseId warehouseId) {

        if((long) appointments.size() >= MAX_APPOINTMENTS_PER_HOUR) {
            throw new MaxNumberOfAppointmentsException("The maximum number for this hour has been reached");
        } else {
            Appointment appointment = new Appointment(
                    new Appointment.AppointmentId(UUID.randomUUID()),
                    sellerId,
                    licensePlate,
                    materialType,
                    new AppointmentWindow(date),
                    warehouseId);

            appointments.add(appointment);
            return appointment;
        }
    }

    public boolean isInAppointmentWindow(Truck truck) {
        return appointments.stream()
                .anyMatch(appointment -> appointment.licensePlate().equals(truck.getLicensePlate()) &&
                        appointment.appointmentWindow().isWithinWindow(truck.getArrivalTime()));
    }


    public Appointment getAppointmentByLicensePlate(LicensePlate licensePlate) {
        return appointments.stream().filter(appointment -> appointment.licensePlate().equals(licensePlate)).findFirst().orElse(null);
    }
}
