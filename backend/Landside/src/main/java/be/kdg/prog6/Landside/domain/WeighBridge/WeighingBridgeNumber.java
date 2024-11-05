package be.kdg.prog6.Landside.domain.WeighBridge;

public record WeighingBridgeNumber(int number) {
    public WeighingBridgeNumber {
        if (number < 1) {
            throw new IllegalArgumentException("WeighingBridgeNumber must not be less than 1");
        }
    }

    public static WeighingBridgeNumber of(Integer number) {
        if (number == null) {
            return null;
        }
        return new WeighingBridgeNumber(number);
    }
}
