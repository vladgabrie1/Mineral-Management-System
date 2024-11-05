package be.kdg.prog6.Landside.ports.out;


import be.kdg.prog6.Landside.domain.LicensePlate;
import be.kdg.prog6.Landside.domain.PayloadDeliveryTicket;

import java.time.LocalDate;
import java.util.List;

public interface LoadPayloadDeliveryTicketPort {
    PayloadDeliveryTicket findPayloadDeliveryTicketByLicensePlate(LicensePlate licensePlate);
    List<PayloadDeliveryTicket> findPayloadDeliveryTicketsByDate(LocalDate date);
}
