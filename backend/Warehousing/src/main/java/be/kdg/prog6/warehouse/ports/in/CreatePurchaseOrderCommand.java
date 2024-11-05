package be.kdg.prog6.warehouse.ports.in;

import be.kdg.prog6.warehouse.domain.Customer;
import be.kdg.prog6.warehouse.domain.PurchaseOrderLine;

import java.time.LocalDate;
import java.util.List;

public record CreatePurchaseOrderCommand(LocalDate date, Customer customer, List<PurchaseOrderLine> purchaseOrderLines) {
}
