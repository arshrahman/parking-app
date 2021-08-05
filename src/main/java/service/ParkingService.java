package service;

import exception.ErrorMessage;
import exception.ParkingException;
import model.ParkingType;
import model.Reservation;
import output.OutputMessages;
import repository.ParkingRepository;

import java.util.Optional;

public class ParkingService {
    private final ParkingRepository parkingRepository;

    public ParkingService() {
        this.parkingRepository = new ParkingRepository();
    }

    public void setParkingLotCapacity(ParkingType parkingLotKey, int capacity) {
        parkingRepository.setParkingLotCapacity(parkingLotKey, capacity);
    }

    public Reservation enter(String parkingType, String vehicleNo, String enterTimestamp) throws ParkingException {
        final Reservation reservation;
        reservation = new Reservation(parkingType, vehicleNo, enterTimestamp);

        if (parkingRepository.isAlreadyParked(reservation.getVehicleNo())) {
            reservation.setReserved(false);
            reservation.setRemarks(OutputMessages.ALREADY_PARKED.replace(OutputMessages.VEHICLE_NO, reservation.getVehicleNo()));
            return reservation;
        }

        if (!parkingRepository.isLotAvailable(reservation.getLot().getParkingType())) {
            reservation.setReserved(false);
            reservation.setRemarks(OutputMessages.PARKING_FULL);
            return reservation;
        }

        final int lotId = parkingRepository.reserveLot(reservation.getLot().getParkingType());
        reservation.getLot().setLotId(lotId);
        reservation.setReserved(true);
        parkingRepository.putReservation(reservation);
        return reservation;
    }

    public Reservation exit(String vehicleNo, String exitTimestamp) throws ParkingException {
        if (!parkingRepository.isAlreadyParked(vehicleNo))
            throw new ParkingException(ErrorMessage.NOT_PARKED);

        final Reservation reservation = parkingRepository.getReservation(vehicleNo);
        reservation.setExitDate(exitTimestamp);
        reservation.setPrice(parkingRepository.getPrice(
                reservation.getLot().getParkingType(), reservation.getEnterDate(), reservation.getExitDate()));
        reservation.setReserved(false);

        parkingRepository.releaseLot(reservation.getLot());
        parkingRepository.removeReservation(vehicleNo);

        return reservation;
    }
}
