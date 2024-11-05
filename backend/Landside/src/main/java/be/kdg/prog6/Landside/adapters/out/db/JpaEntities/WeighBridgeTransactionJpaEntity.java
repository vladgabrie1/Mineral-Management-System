package be.kdg.prog6.Landside.adapters.out.db.JpaEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "weigh_bridge_transactions",catalog = "land")
public class WeighBridgeTransactionJpaEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "license_plate", nullable = false)
    private String licensePlate;

    @Column(name = "arrival_weight", nullable = false)
    private double arrivalWeight;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "tare_weight")
    private int tareWeight;

    @Column(name = "net_weight")
    private Integer netWeight;

    public WeighBridgeTransactionJpaEntity() {
    }

    public WeighBridgeTransactionJpaEntity(UUID id, String licensePlate, double arrivalWeight) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.arrivalWeight = arrivalWeight;
    }

    public WeighBridgeTransactionJpaEntity(UUID id, String licensePlate, double arrivalWeight,
                                  LocalDateTime timestamp, int tareWeight, Integer netWeight) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.arrivalWeight = arrivalWeight;
        this.timestamp = timestamp;
        this.tareWeight = tareWeight;
        this.netWeight = netWeight;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getArrivalWeight() {
        return arrivalWeight;
    }

    public void setArrivalWeight(double arrivalWeight) {
        this.arrivalWeight = arrivalWeight;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getTareWeight() {
        return tareWeight;
    }

    public void setTareWeight(int tareWeight) {
        this.tareWeight = tareWeight;
    }

    public Integer getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(int netWeight) {
        this.netWeight = netWeight;
    }
}
