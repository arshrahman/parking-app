import exception.ErrorMessage;
import exception.ParkingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingAppTest {

    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private final PrintStream consoleOutput = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(consoleOutput);
    }

    @Test
    public void validIntegrationTest() throws ParkingException {
        String validOutput = "Accept MOTORCYCLELOT1\n" +
                "Accept CARLOT1\n" +
                "MOTORCYCLELOT1 2\n" +
                "Accept CARLOT2\n" +
                "Accept CARLOT3\n" +
                "Reject Parking full\n" +
                "CARLOT3 6\n";
        ParkingApp.main(new String[] {"valid_input.txt"});
        assertEquals(validOutput, byteArrayOutputStream.toString());
    }

    @Test
    public void invalidIntegrationTest() throws ParkingException {
        String invalidOutput = "Error Processing ENTER Command. Please pass in valid command arguments\n" +
                "Error Processing ENTER Command. Vehicle enter and exit date should be valid\n" +
                "Error Processing EXIT Command. Please pass in valid command arguments\n" +
                "Error Processing ENTER Command. Vehicle type is invalid\n" +
                "Accept CARLOT1\n" +
                "Accept CARLOT2\n" +
                "CARLOT1 6\n";

        ParkingApp.main(new String[] {"invalid_input.txt"});
        assertEquals(invalidOutput, byteArrayOutputStream.toString());
    }

    @Test
    public void invalidInputFile() {
        Exception exception = assertThrows(ParkingException.class, () -> {
            ParkingApp.main(new String[] {});
        });
        assertEquals(exception.getMessage(), ErrorMessage.INVALID_INPUT_FILE.getMessage());
    }
}
