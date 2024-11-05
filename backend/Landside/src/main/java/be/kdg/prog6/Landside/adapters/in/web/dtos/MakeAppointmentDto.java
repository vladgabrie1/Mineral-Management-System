package be.kdg.prog6.Landside.adapters.in.web.dtos;

import be.kdg.prog6.common.domain.MaterialType;

import java.time.LocalDateTime;
import java.util.UUID;

public class MakeAppointmentDto {

    private UUID sellerId;
    private LocalDateTime date;
    private String licensePlate;
    private MaterialType materialType;

    public MakeAppointmentDto() {
    }

    public MakeAppointmentDto(UUID sellerId, LocalDateTime date, String licensePlate, MaterialType materialType) {
        this.sellerId = sellerId;
        this.date = date;
        this.licensePlate = licensePlate;
        this.materialType = materialType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }
}
