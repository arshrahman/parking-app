package service;

import strategy.parking.ParkingStrategy;
import strategy.pricing.PricingStrategy;

public class MotorCycleParkingLot extends ParkingLot {

    public static String TYPE = "MOTORCYCLE";
    private int capacity = 0;

    public MotorCycleParkingLot(ParkingStrategy parkingStrategy, PricingStrategy pricingStrategy) {
        super(parkingStrategy, pricingStrategy);
    }

    @Override
    public void setCapacity(int size) {
        this.capacity = size;
    }
}
