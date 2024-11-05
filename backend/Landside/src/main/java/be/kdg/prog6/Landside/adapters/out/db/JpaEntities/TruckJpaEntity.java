package be.kdg.prog6.Landside.adapters.out.db.JpaEntities;

import be.kdg.prog6.Landside.domain.TruckLocation;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(catalog = "land", name = "truck")
public class TruckJpaEntity {
    @Id
    @Column(name = "id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "on_time")
    private boolean onTime;

    @Column(name = "location")
    @Enumerated(EnumType.STRING)
    private TruckLocation location;


    public TruckJpaEntity() {
    }

    public TruckJpaEntity(String licensePlate, LocalDateTime arrivalTime, boolean onTime, TruckLocation location) {
        this.licensePlate = licensePlate;
        this.arrivalTime = arrivalTime;
        this.onTime = onTime;
        this.location = location;
    }


    public TruckJpaEntity(UUID id, String licensePlate, LocalDateTime arrivalTime, boolean onTime, TruckLocation location) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.arrivalTime = arrivalTime;
        this.onTime = onTime;
        this.location = location;
    }

    public void setLocation(TruckLocation location) {
        this.location = location;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public boolean isOnTime() {
        return onTime;
    }

    public TruckLocation getLocation() {
        return location;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
