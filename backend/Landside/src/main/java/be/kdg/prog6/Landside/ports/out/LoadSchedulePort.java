package be.kdg.prog6.Landside.ports.out;

import be.kdg.prog6.Landside.domain.HourlySchedule;

import java.time.LocalDateTime;

@FunctionalInterface
public interface LoadSchedulePort {
    HourlySchedule loadScheduleForHour(LocalDateTime dateTime);

}
