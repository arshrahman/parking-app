package repository;

import model.ParkingType;
import strategy.parking.AscendingOrderParkingStrategy;
import strategy.pricing.HourlyPricingStrategy;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotFactory {
    private final Map<ParkingType, ParkingLot> parkingLotMap = new HashMap<>();

    ParkingLotFactory() {
        this.parkingLotMap.put(ParkingType.CAR,
                new CarParkingLot(new AscendingOrderParkingStrategy(), new HourlyPricingStrategy(2.0)));
        this.parkingLotMap.put(ParkingType.MOTORCYCLE,
                new MotorCycleParkingLot(new AscendingOrderParkingStrategy(), new HourlyPricingStrategy(1.0)));
    }

    public Map<ParkingType, ParkingLot> getParkingLotMap() {
        return this.parkingLotMap;
    }
}
