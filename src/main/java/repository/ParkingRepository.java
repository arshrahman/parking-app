package repository;

import exception.ParkingException;
import model.Lot;
import model.ParkingType;
import model.Reservation;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class ParkingRepository {
    private final ParkingLotFactory parkingLotFactory;
    protected Map<String, Reservation> occupiedLots;

    public ParkingRepository() {
        this.parkingLotFactory = new ParkingLotFactory();
        this.occupiedLots = new HashMap<>();
    }

    public void setParkingLotCapacity(ParkingType parkingType, int capacity) {
        parkingLotFactory.getParkingLotMap().get(parkingType).setCapacity(capacity);
    }

    public ParkingLot getParkingLot(ParkingType parkingType) {
        return parkingLotFactory.getParkingLotMap().get(parkingType);
    }

    public boolean isAlreadyParked(String vehicleNo) {
        return occupiedLots.containsKey(vehicleNo);
    }

    public Reservation getReservation(String vehicleNo) {
        return occupiedLots.get(vehicleNo);
    }

    public void putReservation(Reservation reservation) throws ParkingException {
        occupiedLots.put(reservation.getVehicleNo(), reservation);
    }

    public void removeReservation(String vehicleNo) {
        occupiedLots.remove(vehicleNo);
    }

    public boolean isLotAvailable(ParkingType parkingType) {
        return getParkingLot(parkingType).isLotAvailable();
    }

    public int reserveLot(ParkingType parkingType) throws ParkingException {
        return getParkingLot(parkingType).reserve();
    }

    public boolean releaseLot(Lot lot) {
        return getParkingLot(lot.getParkingType()).release(lot.getLotId());
    }

    public double getPrice(ParkingType parkingType, Instant start, Instant end) {
        return getParkingLot(parkingType).calculateFare(start, end);
    }
}
