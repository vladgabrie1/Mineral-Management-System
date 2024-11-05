package be.kdg.prog6.Landside.adapters.in.web;

import be.kdg.prog6.Landside.adapters.in.web.dtos.PdtDTO;
import be.kdg.prog6.Landside.domain.PayloadDeliveryTicket;
import be.kdg.prog6.Landside.ports.in.PDTQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pdt")
public class PDTController {

    private static final Logger logger = LoggerFactory.getLogger(PDTController.class);
    private final PDTQuery pdtQuery;

    public PDTController(PDTQuery pdtQuery) {
        this.pdtQuery = pdtQuery;
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<PdtDTO>> getPDTsByDate(@RequestParam("date") String dateStr) {
        logger.info("Request to get all PDTs for date: {}", dateStr);
        try {
            LocalDate date = LocalDate.parse(dateStr);
            List<PayloadDeliveryTicket> pdtList = pdtQuery.findPDTsByDate(date);

            List<PdtDTO> pdtDTOs = pdtList.stream()
                    .map(this::convertToDto)
                    .toList();

            if (pdtDTOs.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pdtDTOs);
            }
            return ResponseEntity.ok(pdtDTOs);

        } catch (Exception e) {
            logger.error("Error occurred while fetching PDTs: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    private PdtDTO convertToDto(PayloadDeliveryTicket ticket) {
        return new PdtDTO(
                ticket.getWeight().value(),
                ticket.getLicensePlate().licensePlate(),
                ticket.getDeliveryTime(),
                ticket.getMaterialType().toString(),
                ticket.getWarehouseId().id().toString(),
                ticket.getTruck().isOnTime(),
                ticket.getTruck().getArrivalTime()
        );
    }
}
