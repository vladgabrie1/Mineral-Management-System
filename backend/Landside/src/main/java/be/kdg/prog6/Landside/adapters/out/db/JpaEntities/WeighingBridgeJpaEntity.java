package be.kdg.prog6.Landside.adapters.out.db.JpaEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(catalog = "land", name = "weighing_bridges")
public class WeighingBridgeJpaEntity {
    @Id
    @Column(name = "number")
    private Integer number;

    @Column(name = "is_available")
    private boolean isAvailable;

    public WeighingBridgeJpaEntity(int number, boolean isAvailable) {
        this.number = number;
        this.isAvailable = isAvailable;
    }

    public WeighingBridgeJpaEntity() {

    }

    public Integer getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
