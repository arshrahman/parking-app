package service;

import strategy.parking.ParkingStrategy;
import strategy.pricing.PricingStrategy;

public class CarParkingLot extends ParkingLot {

    public static String TYPE = "CAR";
    private int capacity = 0;

    public CarParkingLot(ParkingStrategy parkingStrategy, PricingStrategy pricingStrategy) {
        super(parkingStrategy, pricingStrategy);
    }

    @Override
    public void setCapacity(int size) {
        this.capacity = size;
    }
}
