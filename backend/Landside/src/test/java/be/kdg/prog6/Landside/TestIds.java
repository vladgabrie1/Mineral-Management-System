package be.kdg.prog6.Landside;

import java.util.UUID;
import be.kdg.prog6.Landside.domain.SellerId;
import be.kdg.prog6.Landside.domain.WarehouseId;

public class TestIds {
    public static final SellerId SELLER_ID = new SellerId(UUID.fromString("012f6507-7214-4b7a-9e28-efe5424245ae"));
    public static final WarehouseId WAREHOUSE_ID = new WarehouseId(UUID.fromString("012f6507-7214-4b7a-9e28-efe5424245ae"));

    public TestIds() {
    }
}
