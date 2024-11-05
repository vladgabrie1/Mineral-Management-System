package be.kdg.prog6.Landside.domain;

import java.time.LocalDateTime;

public class AppointmentWindow {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public AppointmentWindow(LocalDateTime startTime) {
        this.startTime = startTime;
        this.endTime = startTime.plusHours(1);
    }

    public AppointmentWindow(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isWithinWindow(LocalDateTime arrivalTime){
        return arrivalTime.isAfter(startTime) || arrivalTime.isEqual(startTime) && arrivalTime.isBefore(endTime);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
