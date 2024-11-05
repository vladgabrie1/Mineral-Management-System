package be.kdg.prog6.Landside.adapters.in.web;

import be.kdg.prog6.Landside.adapters.in.web.dtos.TruckEntryResultDto;
import be.kdg.prog6.Landside.core.TruckEntryResult;
import be.kdg.prog6.Landside.domain.LicensePlate;
import be.kdg.prog6.Landside.ports.in.TruckEntryUseCase;
import be.kdg.prog6.Landside.ports.in.commands.TruckEntryCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/truck/entry")
public class TruckEntryController {
    private final TruckEntryUseCase truckEntryUseCase;

    public TruckEntryController(TruckEntryUseCase truckEntryUseCase) {
        this.truckEntryUseCase = truckEntryUseCase;
    }

    @GetMapping("/{license_plate}/{arrival_time}")
    public ResponseEntity<TruckEntryResultDto> processTruckEntry(@PathVariable("license_plate") final String licensePlate,
                                                                 @PathVariable("arrival_time") final LocalDateTime arrivalTime) {

        TruckEntryCommand command = new TruckEntryCommand(new LicensePlate(licensePlate), arrivalTime);
        TruckEntryResult entryResponse = truckEntryUseCase.truckHasEntered(command);
        return ResponseEntity.ok(TruckEntryResultDto.of(entryResponse));
    }

}
