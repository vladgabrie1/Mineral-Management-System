package be.kdg.prog6.Landside.adapters.out.db;

import be.kdg.prog6.Landside.adapters.out.db.JpaEntities.WeighBridgeTransactionJpaEntity;
import be.kdg.prog6.Landside.adapters.out.db.JpaRepositories.WeighBridgeTransactionJpaRepository;
import be.kdg.prog6.Landside.domain.LicensePlate;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighBridgeTransaction;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighBridgeTransactionId;
import be.kdg.prog6.Landside.domain.WeightInTons;
import be.kdg.prog6.Landside.ports.out.LoadWeighBridgeTransactionPort;
import be.kdg.prog6.Landside.ports.out.SaveWeighBridgeTransactionPort;
import org.springframework.stereotype.Component;

@Component
public class WeighBridgeTransactionDatabaseAdapter implements SaveWeighBridgeTransactionPort, LoadWeighBridgeTransactionPort{
    private final WeighBridgeTransactionJpaRepository wbtRepository;

    public WeighBridgeTransactionDatabaseAdapter(WeighBridgeTransactionJpaRepository wbtRepository) {
        this.wbtRepository = wbtRepository;
    }


    @Override
    public void saveWBT(WeighBridgeTransaction wbt) {
        wbtRepository.save(toWTBJpaEntity(wbt));
    }

    @Override
    public WeighBridgeTransaction loadIncompleteWeighBridgeTransactionByLicensePlate(LicensePlate licensePlate) {
        return wbtRepository.findByLicensePlateAndNetWeightIsNull(licensePlate.licensePlate()).map(this::toWBT).orElse(null);
    }

    private WeighBridgeTransactionJpaEntity toWTBJpaEntity(WeighBridgeTransaction wbt) {
        var netWeight = wbt.getNetWeight() == null ? null : wbt.getNetWeight().value();
        return new WeighBridgeTransactionJpaEntity(
                wbt.getId().id(),
                wbt.licensePlate().licensePlate(),
                wbt.arrivalWeight().value(),
                wbt.getTimestamp(),
                wbt.tareWeight().value(),
                netWeight
        );
    }

    private  WeighBridgeTransaction toWBT(WeighBridgeTransactionJpaEntity wbt) {
        return new WeighBridgeTransaction(
                new WeighBridgeTransactionId(wbt.getId()),
                new LicensePlate(wbt.getLicensePlate()),
                WeightInTons.of(wbt.getArrivalWeight()),
                wbt.getTimestamp(),
                WeightInTons.of(wbt.getTareWeight()),
                wbt.getNetWeight() == null ? WeightInTons.of(0) : WeightInTons.of(wbt.getNetWeight())
        );
    }
}
