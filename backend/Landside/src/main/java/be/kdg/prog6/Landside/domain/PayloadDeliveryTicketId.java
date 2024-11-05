package be.kdg.prog6.Landside.domain;

import java.util.Objects;
import java.util.UUID;

public record PayloadDeliveryTicketId(UUID id) {
    public PayloadDeliveryTicketId{
        Objects.requireNonNull(id);
    }
}
