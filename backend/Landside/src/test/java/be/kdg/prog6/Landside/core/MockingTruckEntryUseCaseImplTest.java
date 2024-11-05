package be.kdg.prog6.Landside.core;

import be.kdg.prog6.Landside.TestIds;
import be.kdg.prog6.Landside.exceptions.TruckException;
import be.kdg.prog6.Landside.domain.*;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighingBridge;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighingBridgeNumber;
import be.kdg.prog6.Landside.ports.in.commands.TruckEntryCommand;
import be.kdg.prog6.Landside.ports.out.LoadSchedulePort;
import be.kdg.prog6.Landside.ports.out.LoadWeighingBridgePort;
import be.kdg.prog6.Landside.ports.out.UpdateTruckPort;
import be.kdg.prog6.Landside.ports.out.UpdateWeighingBridgePort;
import be.kdg.prog6.common.domain.MaterialType;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MockingTruckEntryUseCaseImplTest {
    private TruckEntryUseCaseImpl sut;
    private LoadSchedulePort loadSchedulePort;
    private LoadWeighingBridgePort loadWeighingBridgePort;
    private UpdateWeighingBridgePort updateWeighingBridgePort;
    private UpdateTruckPort updateTruckPort;

    @BeforeEach
    void setUp() {
        loadSchedulePort = mock(LoadSchedulePort.class);
        loadWeighingBridgePort = mock(LoadWeighingBridgePort.class);
        updateWeighingBridgePort = mock(UpdateWeighingBridgePort.class);
        when(loadSchedulePort.loadScheduleForHour(any(LocalDateTime.class))).thenReturn(createHourlySchedule());
        when(loadWeighingBridgePort.getAvailableWeighingBridge()).thenReturn(new WeighingBridge(new WeighingBridgeNumber(1), true));
        updateTruckPort = mock(UpdateTruckPort.class);

        sut = new TruckEntryUseCaseImpl(loadSchedulePort, loadWeighingBridgePort, updateWeighingBridgePort, updateTruckPort);

    }


    private static HourlySchedule createHourlySchedule() {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(
                new Appointment.AppointmentId(UUID.randomUUID()),
                TestIds.SELLER_ID,
                new LicensePlate("ABC123"),
                MaterialType.SLAG,
                new AppointmentWindow(LocalDateTime.of(2025, 9, 25, 15, 0)),
                new WarehouseId(UUID.randomUUID())
        ));

        return new HourlySchedule(appointments);
    }

    @Test
    void shouldReturnWeighBridgeNumberAndTrue() {
        // Act
        final TruckEntryResult result = sut.truckHasEntered(new TruckEntryCommand(new LicensePlate("ABC123"), LocalDateTime.of(2025, 9, 25, 15, 0)));

        // Assert
        assertTrue(result.entryAllowed());
        assertEquals(1, result.weighingBridgeNumber().number());

        final ArgumentCaptor<WeighingBridge> weighingBridgeArgumentCaptor = ArgumentCaptor.forClass(WeighingBridge.class);
        verify(updateWeighingBridgePort).weighingBridgeAvailabilityChanged(weighingBridgeArgumentCaptor.capture());

    }


    @Test
    void shouldThrowExceptionWhenThereIsNoAvailableWeighingBridges(){
        // Arrange
        when(loadWeighingBridgePort.getAvailableWeighingBridge()).thenReturn(null);
        final TruckEntryCommand command = new TruckEntryCommand(new LicensePlate("ABC123"), LocalDateTime.of(2025, 9, 25, 15, 0));

        // Assert
        TruckException exception = assertThrows(TruckException.class, () -> sut.truckHasEntered(command));
        assertEquals("There are no available weighing bridges", exception.getMessage());
    }
}