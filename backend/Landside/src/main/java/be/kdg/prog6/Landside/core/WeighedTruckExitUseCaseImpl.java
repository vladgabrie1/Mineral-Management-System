package be.kdg.prog6.Landside.core;

import be.kdg.prog6.Landside.domain.Truck;
import be.kdg.prog6.Landside.domain.TruckLocation;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighBridgeTransaction;
import be.kdg.prog6.Landside.domain.PayloadDeliveryTicket;
import be.kdg.prog6.Landside.exceptions.TruckException;
import be.kdg.prog6.Landside.ports.in.WeighedTruckExitUseCase;
import be.kdg.prog6.Landside.ports.in.commands.WeighTruckCommand;
import be.kdg.prog6.Landside.ports.out.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeighedTruckExitUseCaseImpl implements WeighedTruckExitUseCase {
    private final LoadWeighBridgeTransactionPort loadWeighBridgeTransactionPort;
    private final LoadPayloadDeliveryTicketPort loadPayloadDeliveryTicketPort;
    private final List<UpdatePayloadDeliveryTicketPort> updatePayloadDeliveryTicketPort;
    private final LoadTruckPort loadTruckPort;
    private final UpdateTruckPort updateTruckPort;

    public WeighedTruckExitUseCaseImpl(LoadWeighBridgeTransactionPort loadWeighBridgeTransactionPort, LoadPayloadDeliveryTicketPort loadPayloadDeliveryTicketPort, List<UpdatePayloadDeliveryTicketPort> updatePayloadDeliveryTicketPort, LoadTruckPort loadTruckPort, UpdateTruckPort updateTruckPort) {
        this.loadWeighBridgeTransactionPort = loadWeighBridgeTransactionPort;
        this.loadPayloadDeliveryTicketPort = loadPayloadDeliveryTicketPort;
        this.updatePayloadDeliveryTicketPort = updatePayloadDeliveryTicketPort;
        this.loadTruckPort = loadTruckPort;
        this.updateTruckPort = updateTruckPort;
    }

    @Override
    public WeighBridgeTransaction truckHasBeenWeighed(WeighTruckCommand weighTruckCommand) {
        WeighBridgeTransaction wbt = loadWeighBridgeTransactionPort.loadIncompleteWeighBridgeTransactionByLicensePlate(weighTruckCommand.licensePlate());
        final Truck truck = loadTruckPort.loadOnSiteTruckByLicensePlate(weighTruckCommand.licensePlate()).orElseThrow(() -> new TruckException("Truck not found"));
        truck.setLocation(TruckLocation.EXITED);

        // Set the tare weight of the truck
        wbt.setTareWeight(weighTruckCommand.weight());
        wbt.setTimestamp(weighTruckCommand.timestamp());

        // Update the Payload Delivery Ticket with the net weight so it can be sent to the warehousing context and invoicing context
        PayloadDeliveryTicket pdt = loadPayloadDeliveryTicketPort.findPayloadDeliveryTicketByLicensePlate(weighTruckCommand.licensePlate());
        pdt.setWeight(wbt.netWeight());
        updatePayloadDeliveryTicketPort.forEach(p -> p.updatePDT(pdt));
        updateTruckPort.updateTruck(truck);
        return wbt;
    }
}
