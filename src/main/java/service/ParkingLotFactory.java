package service;

import strategy.parking.AscendingOrderParkingStrategy;
import strategy.pricing.HourlyPricingStrategy;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotFactory {
    private Map<String, ParkingLot> parkingLotMap = new HashMap<>();

    ParkingLotFactory() {
        this.parkingLotMap.put(CarParkingLot.TYPE, new CarParkingLot(new AscendingOrderParkingStrategy(), new HourlyPricingStrategy()));
        this.parkingLotMap.put(MotorCycleParkingLot.TYPE, new MotorCycleParkingLot(new AscendingOrderParkingStrategy(), new HourlyPricingStrategy()));
    }
}
