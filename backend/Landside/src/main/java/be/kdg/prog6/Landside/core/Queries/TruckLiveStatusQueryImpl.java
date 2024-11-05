package be.kdg.prog6.Landside.core.Queries;

import be.kdg.prog6.Landside.domain.Truck;
import be.kdg.prog6.Landside.ports.in.TruckLiveStatusQuery;
import be.kdg.prog6.Landside.ports.out.LoadTruckPort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TruckLiveStatusQueryImpl implements TruckLiveStatusQuery {
    private final LoadTruckPort loadTruckPort;

    public TruckLiveStatusQueryImpl(LoadTruckPort loadTruckPort) {
        this.loadTruckPort = loadTruckPort;
    }

    @Override
    public List<Truck> getTrucksStatusLive() {
        return loadTruckPort.loadAllTrucksForToday();
    }
}
