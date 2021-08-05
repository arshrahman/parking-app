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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ExitCommandTest {
    private ParkingService parkingService;
    private OutputMode outputMode;
    private Command exitCommand;

    @BeforeEach
    public void setUp() {
        parkingService = mock(ParkingService.class);
        outputMode = mock(OutputMode.class);
        exitCommand = new ExitCommand(parkingService, outputMode);
    }

    @Test()
    public void testInValidArguments() {

        final List<String> args = List.of("invalid");
        Exception exception = assertThrows(ParkingException.class, () -> {
            exitCommand.execute(args);
        });
        assertEquals(exception.getMessage(), ErrorMessage.INVALID_COMMAND_ARGS.getMessage());
    }


    @Test()
    public void testValidExitReservation() throws ParkingException {
        final Reservation reservation = mocKReservation();
        reservation.setExitDate("1613541999");
        reservation.setPrice(1.0);
        reservation.getLot().setLotId(1);

        when(parkingService.exit(anyString(), anyString())).thenReturn(reservation);
        final List<String> args = List.of(reservation.getVehicleNo(), reservation.getExitDate().toString());
        exitCommand.execute(args);
        verify(outputMode).output(String.format("%s %.0f", reservation.getLot().toString(), reservation.getPrice()));
    }


    public Reservation mocKReservation() throws ParkingException {
        return new Reservation(ParkingType.CAR.name(), "SGX1234A", "1613541902");
    }
}
