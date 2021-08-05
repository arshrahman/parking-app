package command;

import exception.ParkingException;
import output.OutputMode;
import service.ParkingService;

import java.util.List;

public abstract class Command {
    protected ParkingService parkingService;
    protected OutputMode outputMode;

    Command(ParkingService parkingService, OutputMode outputMode) {
        this.parkingService = parkingService;
        this.outputMode = outputMode;
    }

    public abstract void execute(List<String> args) throws ParkingException;

}
