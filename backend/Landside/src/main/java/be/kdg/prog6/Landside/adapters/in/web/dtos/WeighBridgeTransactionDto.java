package be.kdg.prog6.Landside.adapters.in.web.dtos;

import java.time.LocalDateTime;

public class WeighBridgeTransactionDto {
        private String id;
        private String licensePlate;
        private double arrivalWeight;
        private LocalDateTime timestamp;
        private double tareWeight;
        private double netWeight;

    public WeighBridgeTransactionDto(String id, String licensePlate, double arrivalWeight, LocalDateTime timestamp, double tareWeight, double netWeight) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.arrivalWeight = arrivalWeight;
        this.timestamp = timestamp;
        this.tareWeight = tareWeight;
        this.netWeight = netWeight;
    }

    public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public double getTareWeight() {
            return tareWeight;
        }

        public void setTareWeight(double tareWeight) {
            this.tareWeight = tareWeight;
        }

        public double getNetWeight() {
            return netWeight;
        }

        public void setNetWeight(double netWeight) {
            this.netWeight = netWeight;
        }
}
