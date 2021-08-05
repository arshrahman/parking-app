package command;

import exception.ErrorMessage;
import exception.ParkingException;
import model.ParkingType;
import output.OutputMode;
import service.ParkingService;

import java.util.List;

public class InitCommand extends Command {

    public static String COMMAND = "INIT";
    public static int COMMAND_ARGS = 2;

    InitCommand(ParkingService parkingService, OutputMode outputMode) {
        super(parkingService, outputMode);
    }

    @Override
    public void execute(List<String> args) throws ParkingException {
        if (args.size() != COMMAND_ARGS)
            throw new ParkingException(ErrorMessage.INVALID_COMMAND);

        parkingService.setParkingLotCapacity(ParkingType.CAR, getSize(args.get(0)));
        parkingService.setParkingLotCapacity(ParkingType.MOTORCYCLE, getSize(args.get(1)));
    }

    private int getSize(String inputValue) throws ParkingException {
        try {
            final int size = Integer.parseInt(inputValue);
            if (size <= 0)
                throw new ParkingException(ErrorMessage.INVALID_PARKING_LOT_SIZE);
            return size;
        } catch (NumberFormatException e) {
            throw new ParkingException(ErrorMessage.INVALID_COMMAND_ARGS);
        }
    }
}
