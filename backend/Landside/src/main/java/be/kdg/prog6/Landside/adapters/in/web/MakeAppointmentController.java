package be.kdg.prog6.Landside.adapters.in.web;

import be.kdg.prog6.Landside.adapters.in.web.dtos.MakeAppointmentDto;
import be.kdg.prog6.Landside.adapters.in.web.dtos.MakeAppointmentResultDto;
import be.kdg.prog6.Landside.domain.Appointment;
import be.kdg.prog6.Landside.domain.LicensePlate;
import be.kdg.prog6.Landside.domain.SellerId;
import be.kdg.prog6.Landside.ports.in.commands.MakeAppointmentCommand;
import be.kdg.prog6.Landside.ports.in.MakeAppointmentUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/appointments")
@RestController
@CrossOrigin(origins = "*")
public class MakeAppointmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MakeAppointmentController.class);
    private final MakeAppointmentUseCase makeAppointmentUseCase;

    public MakeAppointmentController(MakeAppointmentUseCase makeAppointmentUseCase) {
        this.makeAppointmentUseCase = makeAppointmentUseCase;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('seller', 'admin')")
    public ResponseEntity<MakeAppointmentResultDto> makeAppointment(@RequestBody final MakeAppointmentDto makeAppointmentDto,
                                                                    @AuthenticationPrincipal Jwt jwt) {
        LOGGER.info("Received request to make an appointment: {}", makeAppointmentDto);
        MakeAppointmentCommand command = new MakeAppointmentCommand(
                new SellerId(UUID.fromString(jwt.getClaim("sub"))),
                makeAppointmentDto.getDate(),
                new LicensePlate(makeAppointmentDto.getLicensePlate()),
                makeAppointmentDto.getMaterialType()
        );

        Appointment createdAppointment = makeAppointmentUseCase.makeAppointment(command);

        MakeAppointmentResultDto createdAppointmentDto = new MakeAppointmentResultDto(
                createdAppointment.sellerId().id(),
                createdAppointment.appointmentWindow().getStartTime(),
                createdAppointment.appointmentWindow().getEndTime(),
                createdAppointment.licensePlate().licensePlate(),
                createdAppointment.materialType(),
                createdAppointment.warehouseId().id()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointmentDto);

    }
}
