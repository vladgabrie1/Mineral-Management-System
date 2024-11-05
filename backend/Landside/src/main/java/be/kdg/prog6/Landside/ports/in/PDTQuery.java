package be.kdg.prog6.Landside.ports.in;

import be.kdg.prog6.Landside.domain.PayloadDeliveryTicket;

import java.time.LocalDate;
import java.util.List;

public interface PDTQuery {
    List<PayloadDeliveryTicket> findPDTsByDate(LocalDate date);
}
