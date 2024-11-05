package be.kdg.prog6.Landside.core;

import be.kdg.prog6.Landside.domain.Truck;
import be.kdg.prog6.Landside.domain.TruckLocation;
import be.kdg.prog6.Landside.domain.WarehouseId;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighBridgeTransaction;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighBridgeTransactionId;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighingBridge;
import be.kdg.prog6.Landside.exceptions.TruckException;
import be.kdg.prog6.Landside.ports.in.WeighedTruckEntryUseCase;
import be.kdg.prog6.Landside.ports.in.commands.WeighTruckCommand;
import be.kdg.prog6.Landside.ports.out.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WeighedTruckEntryUseCaseImpl implements WeighedTruckEntryUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeighedTruckEntryUseCaseImpl.class);
    private final LoadWeighingBridgePort loadWeighingBridgePort;
    private final SaveWeighBridgeTransactionPort saveWeighBridgeTransactionPort;
    private final LoadSchedulePort loadSchedulePort;
    private final UpdateWeighingBridgePort updateWeighingBridgePort;
    private final LoadTruckPort loadTruckPort;
    private final UpdateTruckPort updateTruckPort;

    public WeighedTruckEntryUseCaseImpl(LoadWeighingBridgePort loadWeighingBridgePort, SaveWeighBridgeTransactionPort saveWeighBridgeTransactionPort, LoadSchedulePort loadSchedulePort, UpdateWeighingBridgePort updateWeighingBridgePort, LoadTruckPort loadTruckPort, UpdateTruckPort updateTruckPort) {
        this.loadWeighingBridgePort = loadWeighingBridgePort;
        this.saveWeighBridgeTransactionPort = saveWeighBridgeTransactionPort;
        this.loadSchedulePort = loadSchedulePort;
        this.updateWeighingBridgePort = updateWeighingBridgePort;
        this.loadTruckPort = loadTruckPort;
        this.updateTruckPort = updateTruckPort;
    }

    @Override
    @Transactional
    public WarehouseId truckHasBeenWeighted(WeighTruckCommand weighTruckCommand) {
         final WeighingBridge weighingBridge = loadWeighingBridgePort.getWeighingBridgeByNumber(weighTruckCommand.weighingBridgeNumber());
         final Truck truck = loadTruckPort.loadOnSiteTruckByLicensePlate(weighTruckCommand.licensePlate()).orElseThrow(() -> new TruckException("Truck not found"));
         truck.setLocation(TruckLocation.BRIDGE);
         /*
         When the truck entries the facility, it gets weighed
         We create an incomplete Weigh Bridge Transaction Document which will be completed and returned on it leaves
         And gets weighed again
          */

         WeighBridgeTransaction wbt = new WeighBridgeTransaction(
                 new WeighBridgeTransactionId(UUID.randomUUID()),
                 weighTruckCommand.licensePlate(),
                 weighTruckCommand.weight()
         );

         saveWeighBridgeTransactionPort.saveWBT( wbt );
         weighingBridge.setAvailable(true);
         updateWeighingBridgePort.weighingBridgeAvailabilityChanged(weighingBridge);
         updateTruckPort.updateTruck(truck);
         // Return the warehouse number ( id for simplicity ) as mentioned in the User Story
         return loadSchedulePort.loadScheduleForHour(weighTruckCommand.timestamp()).getAppointmentByLicensePlate(weighTruckCommand.licensePlate()).warehouseId();
    }
}
