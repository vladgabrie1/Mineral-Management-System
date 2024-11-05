package be.kdg.prog6.Landside.core;

import be.kdg.prog6.Landside.domain.Appointment;
import be.kdg.prog6.Landside.domain.HourlySchedule;
import be.kdg.prog6.Landside.ports.out.UpdateSchedulePort;

public class UpdateSchedulePortStub implements UpdateSchedulePort {
    private HourlySchedule hourlySchedule;
    private Appointment appointment;

    @Override
    public void scheduleUpdated(HourlySchedule schedule) {
        hourlySchedule = schedule;
        appointment = schedule.getAppointments().get(schedule.getAppointments().size() - 1);
    }

    public HourlySchedule getHourlySchedule() {
        return hourlySchedule;
    }

    public Appointment getAppointment() {
        return appointment;
    }
}
