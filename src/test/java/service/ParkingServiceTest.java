package service;

import exception.ErrorMessage;
import exception.ParkingException;
import model.Lot;
import model.ParkingType;
import model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.ParkingRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ParkingServiceTest {
    private ParkingRepository parkingRepository;
    private ParkingService parkingService;

    @BeforeEach
    public void setUp() {
        parkingRepository = mock(ParkingRepository.class);
        parkingService = new ParkingService(parkingRepository);
    }

    @Test
    public void testValidEnter() throws ParkingException {
        final int lotId = 1;

        when(parkingRepository.isAlreadyParked(anyString())).thenReturn(false);
        when(parkingRepository.isLotAvailable(any(ParkingType.class))).thenReturn(true);
        when(parkingRepository.reserveLot(any(ParkingType.class))).thenReturn(lotId);
        doNothing().when(parkingRepository).putReservation(any(Reservation.class));

        final Reservation reservation = parkingService.enter(ParkingType.CAR.name(), "SGX1234A", "1613541902");
        assertEquals(reservation.getLot().getLotId(), lotId);
        assertTrue(reservation.isReserved());
    }

    @Test
    public void testAlreadyParkedReservation() throws ParkingException {
        when(parkingRepository.isAlreadyParked(anyString())).thenReturn(true);
        final Reservation reservation = parkingService.enter(ParkingType.CAR.name(), "SGX1234A", "1613541902");
        assertFalse(reservation.isReserved());
    }

    @Test
    public void testParkingFullReservation() throws ParkingException {
        when(parkingRepository.isAlreadyParked(anyString())).thenReturn(false);
        when(parkingRepository.isLotAvailable(any(ParkingType.class))).thenReturn(false);
        final Reservation reservation = parkingService.enter(ParkingType.CAR.name(), "SGX1234A", "1613541902");
        assertFalse(reservation.isReserved());
    }

    @Test
    public void testValidExit() throws ParkingException {
        final Reservation mockReservation = mocKReservation();
        mockReservation.setReserved(true);
        final double price = 2.0;

        when(parkingRepository.isAlreadyParked(anyString())).thenReturn(true);
        when(parkingRepository.getReservation(anyString())).thenReturn(mockReservation);
        when(parkingRepository.getPrice(any(ParkingType.class), any(), any())).thenReturn(price);
        doNothing().when(parkingRepository).releaseLot(any(Lot.class));
        doNothing().when(parkingRepository).removeReservation(anyString());

        final Reservation reservation = parkingService.exit(mockReservation.getVehicleNo(), "1613545602");
        assertEquals(reservation.getPrice(), price);
        assertFalse(reservation.isReserved());
    }

    @Test
    public void testExitForNotParkedVehicle() {
        when(parkingRepository.isAlreadyParked(anyString())).thenReturn(false);
        Exception exception = assertThrows(ParkingException.class, () -> {
            parkingService.exit("SGX1234A", "1613545602");
        });

        assertEquals(exception.getMessage(), ErrorMessage.NOT_PARKED.getMessage());
    }

    public Reservation mocKReservation() throws ParkingException {
        return new Reservation(ParkingType.CAR.name(), "SGX1234A", "1613541902");
    }

}
