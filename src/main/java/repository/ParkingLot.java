package repository;

import exception.ParkingException;
import strategy.parking.ParkingStrategy;
import strategy.pricing.PricingStrategy;

import java.time.Instant;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ParkingLot {
    protected ParkingStrategy parkingStrategy;
    protected PricingStrategy pricingStrategy;
    protected int capacity;

    public ParkingLot(ParkingStrategy parkingStrategy, PricingStrategy pricingStrategy) {
        this.parkingStrategy = parkingStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public void setCapacity(int size) {
        this.capacity = size;
        parkingStrategy.createLots(IntStream.rangeClosed(1, size).boxed().collect(Collectors.toList()));
    }

    public boolean isLotAvailable() {
        return parkingStrategy.isLotAvailable();
    }

    public int reserve() throws ParkingException {
        final int lotId = parkingStrategy.getLot();
        parkingStrategy.removeLot(lotId);
        return lotId;
    }

    public boolean release(int lotId) {
        return parkingStrategy.addLot(lotId);
    }

    public double calculateFare(Instant start, Instant end) {
        return pricingStrategy.calculateFare(start, end);
    }

}
