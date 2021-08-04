package service;

import strategy.parking.ParkingStrategy;
import strategy.pricing.PricingStrategy;

public abstract class ParkingLot {
    private ParkingStrategy parkingStrategy;
    private PricingStrategy pricingStrategy;

    public ParkingLot(ParkingStrategy parkingStrategy, PricingStrategy pricingStrategy) {
        this.parkingStrategy = parkingStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public abstract void setCapacity(int size);
}
