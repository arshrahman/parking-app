package repository;

import repository.ParkingLot;
import strategy.parking.ParkingStrategy;
import strategy.pricing.PricingStrategy;

public class CarParkingLot extends ParkingLot {

    public CarParkingLot(ParkingStrategy parkingStrategy, PricingStrategy pricingStrategy) {
        super(parkingStrategy, pricingStrategy);
    }
}
