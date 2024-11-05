package be.kdg.prog6.Landside.domain.WeighBridge;

public class WeighingBridge {
    private final WeighingBridgeNumber number;
    private boolean isAvailable;

    public WeighingBridge(WeighingBridgeNumber number) {
        this.number = number;
        isAvailable = false;
    }

    public WeighingBridge(WeighingBridgeNumber number, boolean isAvailable) {
        this.number = number;
        this.isAvailable = isAvailable;
    }

    public WeighingBridgeNumber getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
