package be.kdg.prog6.Landside.ports.in;

import be.kdg.prog6.Landside.domain.Appointment;
import be.kdg.prog6.Landside.ports.in.commands.MakeAppointmentCommand;

@FunctionalInterface
public interface MakeAppointmentUseCase {
    Appointment makeAppointment(MakeAppointmentCommand makeAppointmentCommand);

}
