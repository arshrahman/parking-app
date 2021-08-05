package repository;

import repository.ParkingLot;
import strategy.parking.ParkingStrategy;
import strategy.pricing.PricingStrategy;

public class MotorCycleParkingLot extends ParkingLot {

    public MotorCycleParkingLot(ParkingStrategy parkingStrategy, PricingStrategy pricingStrategy) {
        super(parkingStrategy, pricingStrategy);
    }
}
