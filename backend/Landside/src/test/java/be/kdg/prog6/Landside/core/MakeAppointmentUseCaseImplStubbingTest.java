package be.kdg.prog6.Landside.core;

import be.kdg.prog6.Landside.TestIds;
import be.kdg.prog6.Landside.exceptions.WarehouseException;
import be.kdg.prog6.Landside.domain.Appointment;
import be.kdg.prog6.Landside.domain.AppointmentWindow;
import be.kdg.prog6.Landside.domain.LicensePlate;
import be.kdg.prog6.Landside.ports.in.commands.MakeAppointmentCommand;
import be.kdg.prog6.common.domain.MaterialType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MakeAppointmentUseCaseImplStubbingTest {
    // Sut = system under test
    private MakeAppointmentUseCaseImpl sut;
    private UpdateSchedulePortStub updateSchedulePort;
    private LoadSchedulePortStub loadSchedulePort;
    private LoadWarehousePortStub loadWarehousePort;

    @BeforeEach
    void setUp() {
        updateSchedulePort = new UpdateSchedulePortStub();
        loadSchedulePort = new LoadSchedulePortStub();
        loadWarehousePort = new LoadWarehousePortStub();
        sut = new MakeAppointmentUseCaseImpl(List.of(updateSchedulePort), loadSchedulePort, loadWarehousePort);
    }

    @Test
    void shouldMakeAppointment() {
        // Act
        final Appointment appointment = sut.makeAppointment(new MakeAppointmentCommand(TestIds.SELLER_ID, LocalDateTime.now().plusHours(5), new LicensePlate("ABC123"), MaterialType.SLAG));

        // Assert
        assertEquals(appointment.sellerId(), TestIds.SELLER_ID);
        assertEquals(appointment.licensePlate(), new LicensePlate("ABC123"));
        assertEquals(appointment.materialType(), MaterialType.SLAG);
        assertEquals(appointment.appointmentWindow().getStartTime().truncatedTo(ChronoUnit.MINUTES), new AppointmentWindow(LocalDateTime.now().plusHours(5)).getStartTime().truncatedTo(ChronoUnit.MINUTES));

    }

    // When the material type is CEMENT a full warehouse will be returned
    @Test
    void shouldNotMakeAppointmentWhenWarehouseIsFull() {
        // Act and Assert
        assertThrows(WarehouseException.class, () ->
                sut.makeAppointment(new MakeAppointmentCommand(TestIds.SELLER_ID, LocalDateTime.now().plusHours(5), new LicensePlate("ABC123"), MaterialType.CEMENT))
        );

    }
}
