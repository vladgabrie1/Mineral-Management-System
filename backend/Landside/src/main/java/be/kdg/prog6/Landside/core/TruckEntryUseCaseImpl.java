package be.kdg.prog6.Landside.core;

import be.kdg.prog6.Landside.domain.TruckLocation;
import be.kdg.prog6.Landside.exceptions.TruckException;
import be.kdg.prog6.Landside.domain.HourlySchedule;
import be.kdg.prog6.Landside.domain.Truck;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighingBridge;
import be.kdg.prog6.Landside.ports.in.TruckEntryUseCase;
import be.kdg.prog6.Landside.ports.in.commands.TruckEntryCommand;
import be.kdg.prog6.Landside.ports.out.LoadSchedulePort;
import be.kdg.prog6.Landside.ports.out.LoadWeighingBridgePort;
import be.kdg.prog6.Landside.ports.out.UpdateTruckPort;
import be.kdg.prog6.Landside.ports.out.UpdateWeighingBridgePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TruckEntryUseCaseImpl implements TruckEntryUseCase {
    private final LoadSchedulePort loadSchedulePort;
    private final LoadWeighingBridgePort loadWeighingBridgePort;
    private final UpdateWeighingBridgePort updateWeighingBridgePort;
    private final UpdateTruckPort updateTruck;

    public TruckEntryUseCaseImpl(LoadSchedulePort loadSchedulePort, LoadWeighingBridgePort loadWeighingBridgePort, UpdateWeighingBridgePort updateWeighingBridgePort, UpdateTruckPort saveTruckPort) {
        this.loadSchedulePort = loadSchedulePort;
        this.loadWeighingBridgePort = loadWeighingBridgePort;
        this.updateWeighingBridgePort = updateWeighingBridgePort;
        this.updateTruck = saveTruckPort;
    }

    @Override
    @Transactional
    public TruckEntryResult truckHasEntered(final TruckEntryCommand command) {
        HourlySchedule hourlySchedule = loadSchedulePort.loadScheduleForHour(command.arrivalTime());
        Truck truck = new Truck(new Truck.TruckId(UUID.randomUUID()), command.licensePlate(), command.arrivalTime(), TruckLocation.ENTERED);

        if(!hourlySchedule.isInAppointmentWindow(truck)) {
            truck.setOnTime(false);
        }

        WeighingBridge availableBridge = loadWeighingBridgePort.getAvailableWeighingBridge();
        if(availableBridge == null) {
            throw new TruckException("There are no available weighing bridges");
        }
        availableBridge.setAvailable(false);
        updateWeighingBridgePort.weighingBridgeAvailabilityChanged(availableBridge);
        updateTruck.updateTruck(truck);
        return new TruckEntryResult(true, availableBridge.getNumber());
    }

}
