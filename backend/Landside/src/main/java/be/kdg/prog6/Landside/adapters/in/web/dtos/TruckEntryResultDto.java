package be.kdg.prog6.Landside.adapters.in.web.dtos;

import be.kdg.prog6.Landside.core.TruckEntryResult;


public record TruckEntryResultDto(boolean entryAllowed, int weighingBridgeNumber) {

    public static TruckEntryResultDto of(TruckEntryResult truckEntryResult) {
        return new TruckEntryResultDto(truckEntryResult.entryAllowed(), truckEntryResult.weighingBridgeNumber().number());
    }

    public TruckEntryResultDto{
        if (weighingBridgeNumber <= 0){
            throw new IllegalArgumentException("weightBridgeNumber must be greater than 0");
        }
    }
}






