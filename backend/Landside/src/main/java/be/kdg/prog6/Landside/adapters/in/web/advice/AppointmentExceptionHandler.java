package be.kdg.prog6.Landside.adapters.in.web.advice;

import be.kdg.prog6.Landside.exceptions.TruckException;
import be.kdg.prog6.Landside.exceptions.WarehouseException;
import be.kdg.prog6.Landside.domain.exceptions.MaxNumberOfAppointmentsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppointmentExceptionHandler {

    @ExceptionHandler(WarehouseException.class)
    public ResponseEntity<String> handleWarehouseFullException(WarehouseException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MaxNumberOfAppointmentsException.class)
    public ResponseEntity<String> handleMaxNumberOfAppointmentsException(MaxNumberOfAppointmentsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(TruckException.class)
    public ResponseEntity<String> handleTruckEntryException(TruckException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

}
