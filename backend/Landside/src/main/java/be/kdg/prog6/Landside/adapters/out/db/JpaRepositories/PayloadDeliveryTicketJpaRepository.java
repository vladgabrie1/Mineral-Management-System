package be.kdg.prog6.Landside.adapters.out.db.JpaRepositories;

import be.kdg.prog6.Landside.adapters.out.db.JpaEntities.PayloadDeliveryTicketJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PayloadDeliveryTicketJpaRepository extends JpaRepository<PayloadDeliveryTicketJpaEntity, UUID> {
   Optional<PayloadDeliveryTicketJpaEntity> findByLicensePlateAndWeightIsNull(String s);

   default List<PayloadDeliveryTicketJpaEntity> findAllByDeliveryDate(LocalDate deliveryDate) {
      LocalDateTime startOfDay = deliveryDate.atStartOfDay();
      LocalDateTime endOfDay = deliveryDate.atTime(23, 59, 59, 999999999);
      return findAllByDeliveryTimeBetween(startOfDay, endOfDay);
   }

   List<PayloadDeliveryTicketJpaEntity> findAllByDeliveryTimeBetween(LocalDateTime start, LocalDateTime end);
}
