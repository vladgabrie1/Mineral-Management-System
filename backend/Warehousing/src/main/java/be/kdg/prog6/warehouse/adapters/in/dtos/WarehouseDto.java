package be.kdg.prog6.warehouse.adapters.in.dtos;

public record WarehouseDto(String name, String id, String sellerId, String materialType, Double fillPercentage, Double stock)  {
}
