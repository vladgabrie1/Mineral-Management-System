package be.kdg.prog6.Landside.ports.out;

import be.kdg.prog6.Landside.domain.HourlySchedule;

public interface UpdateSchedulePort {
    void scheduleUpdated(HourlySchedule schedule);
}
