package be.kdg.prog6.Landside.adapters.in.web;

import be.kdg.prog6.Landside.adapters.in.web.dtos.TruckDto;
import be.kdg.prog6.Landside.ports.in.TruckLiveStatusQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trucks")
public class TruckController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TruckController.class);
    private final TruckLiveStatusQuery truckLiveStatusQuery;

    public TruckController(TruckLiveStatusQuery truckLiveStatusQuery) {
        this.truckLiveStatusQuery = truckLiveStatusQuery;
    }

    @GetMapping("/live")
    public List<TruckDto> getTrucksLive() {
        LOGGER.info("Request to get all trucks live status");
        return truckLiveStatusQuery.getTrucksStatusLive().stream().map(truck -> new TruckDto(truck.getLicensePlate().licensePlate(), truck.getArrivalTime(), truck.isOnTime(), truck.getLocation().name())).toList();
    }
}
