package model;

import exception.ErrorMessage;
import exception.ParkingException;

public class Lot {
    private int lotId;
    private ParkingType parkingType;

    public Lot(String parkingType) throws ParkingException {
        try {
            this.parkingType = ParkingType.valueOf(parkingType.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParkingException(ErrorMessage.INVALID_VEHICLE);
        }
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public ParkingType getParkingType() {
        return parkingType;
    }

    public void setParkingType(ParkingType parkingType) {
        this.parkingType = parkingType;
    }

    @Override
    public String toString() {
        return this.parkingType.name() + "LOT" + lotId;
    }

}
