package be.kdg.prog6.Landside.adapters.out.db;

import be.kdg.prog6.Landside.adapters.out.db.JpaEntities.WeighingBridgeJpaEntity;
import be.kdg.prog6.Landside.adapters.out.db.JpaRepositories.WeighingBridgeJpaRepository;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighingBridge;
import be.kdg.prog6.Landside.domain.WeighBridge.WeighingBridgeNumber;
import be.kdg.prog6.Landside.ports.out.LoadWeighingBridgePort;
import be.kdg.prog6.Landside.ports.out.UpdateWeighingBridgePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WeighingBridgePortDatabaseAdapter implements UpdateWeighingBridgePort, LoadWeighingBridgePort {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeighingBridgePortDatabaseAdapter.class);
    private final WeighingBridgeJpaRepository weighingBridgeJpaRepository;

    public WeighingBridgePortDatabaseAdapter(WeighingBridgeJpaRepository weighingBridgeJpaRepository) {
        this.weighingBridgeJpaRepository = weighingBridgeJpaRepository;
    }

    @Override
    public WeighingBridge getAvailableWeighingBridge() {
        return weighingBridgeJpaRepository.findByIsAvailable(true)
                .stream()
                .findAny()
                .map(this::toWeighingBridge)
                .orElse(null);
    }

    @Override
    public WeighingBridge getWeighingBridgeByNumber(WeighingBridgeNumber number) {
        return toWeighingBridge(weighingBridgeJpaRepository.findByNumber(number.number()));
    }

    @Override
    public void weighingBridgeAvailabilityChanged(WeighingBridge weighingBridge) {
        LOGGER.info("Saving Weighing Bridge number: {} with availability: {}", weighingBridge.getNumber().number(), weighingBridge.isAvailable());
        weighingBridgeJpaRepository.save(toWeighingBridgeJpa(weighingBridge));
    }

    private WeighingBridge toWeighingBridge(WeighingBridgeJpaEntity weighingBridgeJpaEntity) {
        return new WeighingBridge(
                WeighingBridgeNumber.of(weighingBridgeJpaEntity.getNumber()),
                weighingBridgeJpaEntity.isAvailable()
        );
    }

    private WeighingBridgeJpaEntity toWeighingBridgeJpa( WeighingBridge weighingBridge) {
        return new WeighingBridgeJpaEntity(
                weighingBridge.getNumber().number(),
                weighingBridge.isAvailable()
        );
    }
}
