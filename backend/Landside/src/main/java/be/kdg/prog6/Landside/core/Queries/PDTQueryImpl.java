package be.kdg.prog6.Landside.core.Queries;

import be.kdg.prog6.Landside.domain.PayloadDeliveryTicket;
import be.kdg.prog6.Landside.ports.in.PDTQuery;
import be.kdg.prog6.Landside.ports.out.LoadPayloadDeliveryTicketPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PDTQueryImpl implements PDTQuery {
    private final LoadPayloadDeliveryTicketPort loadPayloadDeliveryTicketPort;

    public PDTQueryImpl(LoadPayloadDeliveryTicketPort loadPayloadDeliveryTicketPort) {
        this.loadPayloadDeliveryTicketPort = loadPayloadDeliveryTicketPort;
    }

    @Override
    public List<PayloadDeliveryTicket> findPDTsByDate(LocalDate date) {
        return loadPayloadDeliveryTicketPort.findPayloadDeliveryTicketsByDate(date);
    }
}
