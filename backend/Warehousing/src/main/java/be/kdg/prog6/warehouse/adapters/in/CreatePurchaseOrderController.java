package be.kdg.prog6.warehouse.adapters.in;

import be.kdg.prog6.warehouse.adapters.in.dtos.CreatePurchaseOrderDto;
import be.kdg.prog6.warehouse.domain.*;
import be.kdg.prog6.warehouse.ports.in.CreatePurchaseOrderCommand;
import be.kdg.prog6.warehouse.ports.in.CreatePurchaseOrderUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase-orders")
public class CreatePurchaseOrderController {
    private static final Logger log = LoggerFactory.getLogger(CreatePurchaseOrderController.class);
    private final CreatePurchaseOrderUseCase createPurchaseOrderUseCase;

    public CreatePurchaseOrderController(CreatePurchaseOrderUseCase createPurchaseOrderUseCase) {
        this.createPurchaseOrderUseCase = createPurchaseOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> createPurchaseOrder(@RequestBody CreatePurchaseOrderDto requestDto) {
        log.info("Creating purchase order");

        CreatePurchaseOrderCommand command = new CreatePurchaseOrderCommand(
                requestDto.getDate(),
                new Customer(
                        new CustomerNumber(requestDto.getCustomerNumber()),
                        requestDto.getCustomerName()
                ),
                requestDto.getPurchaseOrderLines().stream()
                        .map(line -> new PurchaseOrderLine(
                                new WeightInTons(line.getWeightInTons()),
                                RawMaterial.valueOf(line.getRawMaterial())
                        ))
                        .toList()
        );

        createPurchaseOrderUseCase.createPurchaseOrder(command);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
