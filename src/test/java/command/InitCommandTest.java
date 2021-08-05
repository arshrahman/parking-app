package command;

import exception.ErrorMessage;
import exception.ParkingException;
import model.ParkingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import output.OutputMode;
import service.ParkingService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class InitCommandTest {
    private ParkingService parkingService;
    private Command initCommand;

    @BeforeEach
    public void setUp() {
        parkingService = mock(ParkingService.class);
        initCommand = new InitCommand(parkingService, mock(OutputMode.class));
    }

    @Test()
    public void testInValidArguments() {

        doNothing().when(parkingService).setParkingLotCapacity(ParkingType.CAR, 10);
        doNothing().when(parkingService).setParkingLotCapacity(ParkingType.MOTORCYCLE, 10);

        final List<String> args = List.of("invalid");
        Exception exception = assertThrows(ParkingException.class, () -> {
            initCommand.execute(args);
        });
        assertEquals(exception.getMessage(), ErrorMessage.INVALID_COMMAND.getMessage());
    }

    @Test()
    public void testInValidParkingSize() {

        final List<String> args = List.of("-4", "-3");
        Exception exception = assertThrows(ParkingException.class, () -> {
            initCommand.execute(args);
        });
        assertEquals(exception.getMessage(), ErrorMessage.INVALID_PARKING_LOT_SIZE.getMessage());
    }

}
