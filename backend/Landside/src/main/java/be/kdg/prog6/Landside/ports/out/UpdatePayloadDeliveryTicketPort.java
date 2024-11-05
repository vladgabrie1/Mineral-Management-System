package be.kdg.prog6.Landside.ports.out;

import be.kdg.prog6.Landside.domain.PayloadDeliveryTicket;

@FunctionalInterface
public interface UpdatePayloadDeliveryTicketPort {
    void updatePDT(PayloadDeliveryTicket pdt);
}
