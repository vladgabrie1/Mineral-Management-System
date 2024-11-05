package be.kdg.prog6.Landside.adapters.in.web;

import be.kdg.prog6.Landside.adapters.in.web.dtos.PayloadDeliveryTicketDto;
import be.kdg.prog6.Landside.domain.LicensePlate;
import be.kdg.prog6.Landside.domain.PayloadDeliveryTicket;
import be.kdg.prog6.Landside.ports.in.DockTruckUseCase;
import be.kdg.prog6.Landside.ports.in.commands.DockTruckCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/truck/dock/")
public class DockTruckController {
    private final DockTruckUseCase dockTruckUseCase;

    public DockTruckController(DockTruckUseCase dockTruckUseCase) {
        this.dockTruckUseCase = dockTruckUseCase;
    }

    @RequestMapping("/{license_plate}/{timestamp}")
    private ResponseEntity<PayloadDeliveryTicketDto> dockTruck(@PathVariable("license_plate") final String licensePlate,
                                                               @PathVariable("timestamp") final LocalDateTime dockingTime){
        DockTruckCommand command = new DockTruckCommand(new LicensePlate(licensePlate), dockingTime);

        PayloadDeliveryTicket pdt = dockTruckUseCase.dockTruck(command);

        PayloadDeliveryTicketDto payloadDelTicketDto = new PayloadDeliveryTicketDto(
                pdt.getId().id(),
                pdt.getLicensePlate().licensePlate(),
                pdt.getDeliveryTime(),
                pdt.getMaterialType(),
                pdt.getWarehouseId().id()
        );

        return ResponseEntity.ok(payloadDelTicketDto);
    }
}