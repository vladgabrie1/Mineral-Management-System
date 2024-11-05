package be.kdg.prog6.Landside.ports.in;

import be.kdg.prog6.Landside.domain.Truck;

import java.util.List;

public interface TruckLiveStatusQuery {
    List<Truck> getTrucksStatusLive();
}
