package be.kdg.prog6.warehouse.domain;

public enum RawMaterial {
    GYPSUM("Gypsum", 13, 1, "A soft sulfate mineral used in construction and agriculture."),
    IRON_ORE("Iron Ore", 110, 5, "A crucial raw material for steel production."),
    CEMENT("Cement", 95, 3, "A binder substance used in construction."),
    PETCOKE("Petcoke", 210, 10, "A carbon-rich material derived from oil refining."),
    SLAG("Slag", 60, 2, "A byproduct of the smelting process, used in construction.");

    private final String name;
    private final double pricePerTon;
    private final double storagePricePerTonPerDay;
    private final String description;

    RawMaterial(String name, double pricePerTon, double storagePricePerTonPerDay, String description) {
        this.name = name;
        this.pricePerTon = pricePerTon;
        this.storagePricePerTonPerDay = storagePricePerTonPerDay;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPricePerTon() {
        return pricePerTon;
    }

    public double getStoragePricePerTonPerDay() {
        return storagePricePerTonPerDay;
    }

    public String getDescription() {
        return description;
    }
}