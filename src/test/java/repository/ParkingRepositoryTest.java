package repository;

import exception.ParkingException;
import model.ParkingType;
import model.Reservation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingRepositoryTest {
    private final ParkingRepository parkingRepository = new ParkingRepository();

    @Test
    public void testValidParkingRepositoryExecution() throws ParkingException {
        final ParkingType parkingType = ParkingType.CAR;
        final Reservation mockReservation = mocKReservation();

        parkingRepository.setParkingLotCapacity(parkingType, 2);
        assertTrue(parkingRepository.isLotAvailable(parkingType));

        parkingRepository.putReservation(mockReservation);
        assertTrue(parkingRepository.isAlreadyParked(mockReservation.getVehicleNo()));
        assertEquals(parkingRepository.getReservation(mockReservation.getVehicleNo()).getVehicleNo(), mockReservation.getVehicleNo());

        parkingRepository.removeReservation(mockReservation.getVehicleNo());
        assertFalse(parkingRepository.isAlreadyParked(mockReservation.getVehicleNo()));
    }

    public Reservation mocKReservation() throws ParkingException {
        return new Reservation(ParkingType.CAR.name(), "SGX1234A", "1613541902");
    }
}
