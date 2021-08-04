import exception.InvalidEntryException;
import input.FileInput;
import output.ConsoleOutput;

import java.io.IOException;

public class ParkingApp {

    public static void main(String[] args) throws IOException {
        startParkingApp(args);
    }

    private static void startParkingApp(String[] args) throws IOException {
        if (args.length == 1)
            new FileInput(args[0], new ConsoleOutput()).process();
        else
            throw new InvalidEntryException();
    }

}