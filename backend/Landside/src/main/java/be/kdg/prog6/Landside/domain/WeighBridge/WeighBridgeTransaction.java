package be.kdg.prog6.Landside.domain.WeighBridge;

import be.kdg.prog6.Landside.domain.LicensePlate;
import be.kdg.prog6.Landside.domain.WeightInTons;

import java.time.LocalDateTime;


public final class WeighBridgeTransaction {
    private final WeighBridgeTransactionId id;
    private final LicensePlate licensePlate;
    private final WeightInTons arrivalWeight;
    private  LocalDateTime timestamp;
    private  WeightInTons tareWeight;
    private  WeightInTons netWeight;

    public WeighBridgeTransaction(WeighBridgeTransactionId id, LicensePlate licensePlate, WeightInTons arrivalWeight,
                                  LocalDateTime timestamp, WeightInTons tareWeight, WeightInTons netWeight) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.arrivalWeight = arrivalWeight;
        this.timestamp = timestamp;
        this.tareWeight = tareWeight;
        this.netWeight = netWeight;
    }

    public WeighBridgeTransaction(WeighBridgeTransactionId id, LicensePlate licensePlate, WeightInTons weight) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.arrivalWeight = weight;
        this.timestamp = LocalDateTime.now();
        this.tareWeight = WeightInTons.of(0);
        this.netWeight = null;
    }

    public WeighBridgeTransactionId id() {
        return id;
    }

    public LicensePlate licensePlate() {
        return licensePlate;
    }

    public WeightInTons arrivalWeight() {
        return arrivalWeight;
    }

    public LocalDateTime timestamp() {
        return timestamp;
    }

    public WeightInTons tareWeight() {
        return tareWeight;
    }

    public WeightInTons netWeight() {
        return netWeight;
    }

    public WeighBridgeTransactionId getId() {
        return id;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public WeightInTons getArrivalWeight() {
        return arrivalWeight;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public WeightInTons getTareWeight() {
        return tareWeight;
    }

    /*
    The tare weight is the weight of the truck without any load
     */
    public void setTareWeight(WeightInTons tareWeight) {
        this.tareWeight = tareWeight;
        /*
         If the tare weight is set, the net weight ( how much was delivered can be calculated)
         */
        setNetWeight( arrivalWeight.sub(tareWeight));
    }


    public WeightInTons getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(WeightInTons netWeight) {
        this.netWeight = netWeight;
    }
}
