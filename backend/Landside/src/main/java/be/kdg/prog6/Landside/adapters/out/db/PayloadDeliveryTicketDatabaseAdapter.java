package be.kdg.prog6.Landside.adapters.out.db;

import be.kdg.prog6.Landside.adapters.out.db.JpaEntities.PayloadDeliveryTicketJpaEntity;
import be.kdg.prog6.Landside.adapters.out.db.JpaEntities.TruckJpaEntity;
import be.kdg.prog6.Landside.adapters.out.db.JpaRepositories.PayloadDeliveryTicketJpaRepository;
import be.kdg.prog6.Landside.domain.*;
import be.kdg.prog6.Landside.ports.out.LoadPayloadDeliveryTicketPort;
import be.kdg.prog6.Landside.ports.out.SavePayloadDeliveryTicketPort;
import be.kdg.prog6.Landside.ports.out.UpdatePayloadDeliveryTicketPort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PayloadDeliveryTicketDatabaseAdapter implements SavePayloadDeliveryTicketPort, LoadPayloadDeliveryTicketPort, UpdatePayloadDeliveryTicketPort {
    private final PayloadDeliveryTicketJpaRepository pdtRepository;

    public PayloadDeliveryTicketDatabaseAdapter(PayloadDeliveryTicketJpaRepository pdtRepository) {
        this.pdtRepository = pdtRepository;
    }

    @Override
    public void savePayloadDeliveryTicket(PayloadDeliveryTicket payloadDeliveryTicket) {
        pdtRepository.save(toPDTJpaEntity(payloadDeliveryTicket));
    }

    @Override
    public PayloadDeliveryTicket findPayloadDeliveryTicketByLicensePlate(LicensePlate licensePlate) {
        return pdtRepository.findByLicensePlateAndWeightIsNull(licensePlate.licensePlate())
                .map(this::toPDT)
                .orElseThrow(() -> new IllegalArgumentException("No unfinished weigh bridge transaction found for license plate " + licensePlate.licensePlate()));
    }

    @Override
    public void updatePDT(PayloadDeliveryTicket pdt) {
        pdtRepository.findById(pdt.getId().id())
        .map(pdtJpaEntity -> {
            pdtJpaEntity.setWeight(pdt.getWeight().value());
            return pdtRepository.save(pdtJpaEntity);
        })
        .orElseThrow(() -> new IllegalArgumentException("No payload delivery ticket found with id " + pdt.getId().id()));
    }

    @Override
    public List<PayloadDeliveryTicket> findPayloadDeliveryTicketsByDate(LocalDate date) {
        return pdtRepository.findAllByDeliveryDate(date)
                .stream()
                .map(this::toPDT)
                .toList();
    }

    private PayloadDeliveryTicketJpaEntity toPDTJpaEntity(PayloadDeliveryTicket pdt) {
        return new PayloadDeliveryTicketJpaEntity(
                pdt.getId().id(),
                pdt.getWeight() == null ? null : pdt.getWeight().value(),
                pdt.getLicensePlate().licensePlate(),
                pdt.getDeliveryTime(),
                pdt.getMaterialType(),
                pdt.getWarehouseId().id(),
                toTruckJpaEntity(pdt.getTruck())

        );
    }


    private PayloadDeliveryTicket toPDT(PayloadDeliveryTicketJpaEntity jpaEntity) {
        return new PayloadDeliveryTicket(
                new PayloadDeliveryTicketId(jpaEntity.getId()),
                new LicensePlate(jpaEntity.getLicensePlate()),
                jpaEntity.getDeliveryTime(),
                jpaEntity.getMaterialType(),
                new WarehouseId(jpaEntity.getWarehouseId()),
                jpaEntity.getWeight() == null ? WeightInTons.of(0) : WeightInTons.of(jpaEntity.getWeight()),
                toTruck(jpaEntity.getTruck())
        );
    }

    private TruckJpaEntity toTruckJpaEntity(Truck truck) {
        return new TruckJpaEntity(
                truck.getId().id(),
                truck.getLicensePlate().licensePlate(),
                truck.getArrivalTime(),
                truck.isOnTime(),
                truck.getLocation()
        );
    }

    private Truck toTruck(TruckJpaEntity truckJpaEntity) {
        return new Truck(
                new Truck.TruckId(truckJpaEntity.getId()),
                new LicensePlate(truckJpaEntity.getLicensePlate()),
                truckJpaEntity.getArrivalTime(),
                truckJpaEntity.isOnTime(),
                truckJpaEntity.getLocation()
        );
    }



}
