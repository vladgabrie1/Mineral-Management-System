package be.kdg.prog6.Landside.adapters.in.web;

import be.kdg.prog6.Landside.adapters.in.web.dtos.WeighBridgeTransactionDto;
import be.kdg.prog6.Landside.domain.LicensePlate;
import be.kdg.prog6.Landside.domain.WarehouseId;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighBridgeTransaction;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighingBridgeNumber;
import be.kdg.prog6.Landside.domain.WeightInTons;
import be.kdg.prog6.Landside.ports.in.WeighedTruckEntryUseCase;
import be.kdg.prog6.Landside.ports.in.WeighedTruckExitUseCase;
import be.kdg.prog6.Landside.ports.in.commands.WeighTruckCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/weighed/truck")
public class WeighedTruckController {
    private final WeighedTruckEntryUseCase weighTruckEntryUseCase;
    private final WeighedTruckExitUseCase weighedTruckExitUseCase;

    public WeighedTruckController(WeighedTruckEntryUseCase weighTruckEntryUseCase, WeighedTruckExitUseCase weighedTruckExitUseCase) {
        this.weighTruckEntryUseCase = weighTruckEntryUseCase;
        this.weighedTruckExitUseCase = weighedTruckExitUseCase;
    }

    @GetMapping("/entry/{license_plate}/{weighing_bridge_number}/{weight}/{timestamp}")
    public ResponseEntity<String> weighTruckEntry(@PathVariable("license_plate") final String licensePlate,
                                                  @PathVariable("weighing_bridge_number") final int weighingBridgeNumber,
                                                  @PathVariable("weight") final int weight,
                                                  @PathVariable("timestamp") final LocalDateTime timestamp) {

        WeighTruckCommand weighTruckCommand = new WeighTruckCommand(new LicensePlate(licensePlate), new WeighingBridgeNumber(weighingBridgeNumber), new WeightInTons(weight), timestamp);
        WarehouseId response = weighTruckEntryUseCase.truckHasBeenWeighted(weighTruckCommand);
        return ResponseEntity.ok(response.id().toString());
    }

    @GetMapping("/exit/{license_plate}/{weighing_bridge_number}/{weight}/{timestamp}")
    public ResponseEntity<WeighBridgeTransactionDto> weighTruckExit(@PathVariable("license_plate") final String licensePlate,
                                                                    @PathVariable("weighing_bridge_number") final int weighingBridgeNumber,
                                                                    @PathVariable("weight") final int weight,
                                                                    @PathVariable("timestamp") final LocalDateTime timestamp) {
        WeighTruckCommand weighTruckCommand = new WeighTruckCommand(
                new LicensePlate(licensePlate),
                WeighingBridgeNumber.of(weighingBridgeNumber),
                WeightInTons.of(weight),
                timestamp);

        WeighBridgeTransaction wbt = weighedTruckExitUseCase.truckHasBeenWeighed(weighTruckCommand);
        WeighBridgeTransactionDto weighBridgeTranDto = new WeighBridgeTransactionDto(
                wbt.id().id().toString(),
                wbt.licensePlate().licensePlate(),
                wbt.arrivalWeight().value(),
                wbt.timestamp(),
                wbt.tareWeight().value(),
                wbt.netWeight().value()
        );
        return ResponseEntity.ok(weighBridgeTranDto);

    }
}
