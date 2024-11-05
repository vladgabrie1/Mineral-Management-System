package be.kdg.prog6.warehouse.domain;


public record WeightInTons(double value) {

    public WeightInTons{
        if(value < 0){
            throw new IllegalArgumentException("Value cannot be negative");
        }
    }

    public static WeightInTons of(double value) {
        return new WeightInTons((int) Math.round(value));
    }

    public WeightInTons add(WeightInTons tonnage) {
        return new WeightInTons(this.value + tonnage.value);
    }

    public WeightInTons sub(WeightInTons tonnage) {
        return new WeightInTons(this.value - tonnage.value);
    }

    public WeightInTons negate() {
        return new WeightInTons(-this.value);
    }
}
