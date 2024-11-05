package be.kdg.prog6.Landside.domain;

import java.util.Objects;

public record LicensePlate(String licensePlate) {
    public LicensePlate{
        Objects.requireNonNull(licensePlate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicensePlate that = (LicensePlate) o;
        if (that.licensePlate().equals(this.licensePlate())) return true;
        return Objects.equals(licensePlate, that.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(licensePlate);
    }
}
