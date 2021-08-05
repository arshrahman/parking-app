import command.CommandFactory;
import exception.ErrorMessage;
import exception.ParkingException;
import input.FileInput;
import output.ConsoleOutput;
import service.ParkingService;

public class ParkingApp {

    public static void main(String[] args) throws ParkingException {
        startParkingApp(args);
    }

    private static void startParkingApp(String[] args) throws ParkingException {
        if (args.length == 1) {
            final CommandFactory commandFactory = new CommandFactory(new ParkingService(), new ConsoleOutput());
            new FileInput(args[0], commandFactory).run();
        }
        else
            throw new ParkingException(ErrorMessage.INVALID_ENTRY);
    }

}