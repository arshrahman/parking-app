package model;

import exception.ErrorMessage;
import exception.ParkingException;

import java.time.Instant;

public class Reservation {
    private String vehicleNo;
    private Instant enterDate;
    private Instant exitDate;
    private double price;
    private boolean isReserved;
    private String remarks;
    private Lot lot;

    public Reservation(String parkingType, String vehicleNo, String enterTimeStamp) throws ParkingException {
        this.lot = new Lot(parkingType);
        this.vehicleNo = vehicleNo.trim().toUpperCase();
        this.enterDate = convertStringToInstant(enterTimeStamp);

    }

    public Instant convertStringToInstant(String timestamp) throws ParkingException {
        try {
            return Instant.ofEpochSecond(Long.parseLong(timestamp.trim()));
        } catch (NumberFormatException e) {
            throw new ParkingException(ErrorMessage.INVALID_DATE);
        }
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Instant getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Instant enterDate) {
        this.enterDate = enterDate;
    }

    public Instant getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitTimestamp) throws ParkingException {
        this.exitDate = convertStringToInstant(exitTimestamp);
        if (this.enterDate == null || this.enterDate.isAfter(this.exitDate))
            throw new ParkingException(ErrorMessage.INVALID_DATE);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        this.isReserved = reserved;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
