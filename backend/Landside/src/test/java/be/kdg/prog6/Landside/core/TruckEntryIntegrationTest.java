package be.kdg.prog6.Landside.core;

import be.kdg.prog6.Landside.TestIds;
import be.kdg.prog6.Landside.domain.*;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighingBridge;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighingBridgeNumber;
import be.kdg.prog6.Landside.ports.in.TruckEntryUseCase;
import be.kdg.prog6.Landside.ports.in.commands.TruckEntryCommand;
import be.kdg.prog6.Landside.ports.out.LoadSchedulePort;
import be.kdg.prog6.Landside.ports.out.LoadWeighingBridgePort;
import be.kdg.prog6.Landside.ports.out.UpdateTruckPort;
import be.kdg.prog6.Landside.ports.out.UpdateWeighingBridgePort;
import be.kdg.prog6.common.domain.MaterialType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class TruckEntryIntegrationTest {
    @MockBean
    LoadSchedulePort loadSchedulePort;

    @MockBean
    LoadWeighingBridgePort loadWeighingBridgePort;

    @MockBean
    UpdateWeighingBridgePort updateWeighingBridgePort;

    @MockBean
    UpdateTruckPort updateTruckPort;

    @Captor
    private ArgumentCaptor<WeighingBridge> weighingBridgeArgumentCaptor;

    @Test
    void testTruckEntry() {
        Appointment.AppointmentId appointmentId = new Appointment.AppointmentId(UUID.randomUUID());
        LicensePlate licensePlate = new LicensePlate("ABC123");
        MaterialType materialType = MaterialType.CEMENT;
        AppointmentWindow appointmentWindow = new AppointmentWindow(LocalDateTime.of(2025, 9, 25, 15, 0));

        List<Appointment> appointments = List.of(new Appointment(
                appointmentId,
                TestIds.SELLER_ID,
                licensePlate,
                materialType,
                appointmentWindow,
                TestIds.WAREHOUSE_ID
        ));
        HourlySchedule hourlySchedule = Mockito.spy(new HourlySchedule(appointments));
        when(loadSchedulePort.loadScheduleForHour(any())).thenReturn(hourlySchedule);
        when(loadWeighingBridgePort.getAvailableWeighingBridge()).thenReturn(new WeighingBridge(WeighingBridgeNumber.of(1), true));


        TruckEntryUseCase truckEntryUseCase = new TruckEntryUseCaseImpl(loadSchedulePort, loadWeighingBridgePort, updateWeighingBridgePort, updateTruckPort);
        TruckEntryResult truckEntryResult = truckEntryUseCase.truckHasEntered(new TruckEntryCommand(licensePlate, LocalDateTime.of(2025, 9, 25, 15, 10)));

        // verify that the weighing bridge was updated
        verify(updateWeighingBridgePort).weighingBridgeAvailabilityChanged(weighingBridgeArgumentCaptor.capture());
        assertEquals(WeighingBridgeNumber.of(1), weighingBridgeArgumentCaptor.getValue().getNumber());
        assertFalse(weighingBridgeArgumentCaptor.getValue().isAvailable());

        verify(hourlySchedule, times(1)).isInAppointmentWindow(any(Truck.class));

    }
}