package command;

import exception.ErrorMessage;
import exception.ParkingException;
import model.ParkingType;
import model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import output.OutputMessages;
import output.OutputMode;
import service.ParkingService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class EnterCommandTest {

    private ParkingService parkingService;
    private OutputMode outputMode;
    private Command enterCommand;

    @BeforeEach
    public void setUp() {
        parkingService = mock(ParkingService.class);
        outputMode = mock(OutputMode.class);
        enterCommand = new EnterCommand(parkingService, outputMode);
    }

    @Test
    public void testAcceptReservation() throws ParkingException {
        final Reservation reservation = mocKReservation();
        reservation.setReserved(true);
        reservation.getLot().setLotId(1);

        when(parkingService.enter(anyString(), anyString(), anyString())).thenReturn(reservation);
        final List<String> args = List.of(ParkingType.CAR.name(), "SGX1234A", "1613541902");
        enterCommand.execute(args);
        verify(outputMode).output(OutputMessages.ACCEPT.replace(OutputMessages.LOT_ID, reservation.getLot().toString()));
    }

    @Test
    public void testRejectReservation() throws ParkingException {
        final Reservation reservation = mocKReservation();
        reservation.setReserved(false);
        reservation.setRemarks("");

        when(parkingService.enter(anyString(), anyString(), anyString())).thenReturn(reservation);
        final List<String> args = List.of(ParkingType.CAR.name(), "SGX1234A", "1613541902");
        enterCommand.execute(args);
        verify(outputMode).output(OutputMessages.REJECT);
    }

    @Test()
    public void testInValidArguments() {

        final List<String> args = List.of("invalid");
        Exception exception = assertThrows(ParkingException.class, () -> {
            enterCommand.execute(args);
        });
        assertEquals(exception.getMessage(), ErrorMessage.INVALID_COMMAND_ARGS.getMessage());
    }

    public Reservation mocKReservation() throws ParkingException {
        return new Reservation(ParkingType.CAR.name(), "SGX1234A", "1613541902");
    }

}
