package be.kdg.prog6.Landside.adapters.out.db.JpaRepositories;

import be.kdg.prog6.Landside.adapters.out.db.JpaEntities.WeighingBridgeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeighingBridgeJpaRepository extends JpaRepository<WeighingBridgeJpaEntity, Integer> {

    List<WeighingBridgeJpaEntity> findByIsAvailable(boolean available);

    WeighingBridgeJpaEntity findByNumber(int number);
}
