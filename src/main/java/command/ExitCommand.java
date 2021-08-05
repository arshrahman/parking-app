package command;

import exception.ErrorMessage;
import exception.ParkingException;
import model.Reservation;
import output.OutputMode;
import service.ParkingService;

import java.util.List;

public class ExitCommand extends Command{

    public static String COMMAND = "EXIT";
    public static int COMMAND_ARGS = 2;

    ExitCommand(ParkingService parkingService, OutputMode outputMode) {
        super(parkingService, outputMode);
    }

    @Override
    public void execute(List<String> args) throws ParkingException {
        if (args.size() != COMMAND_ARGS)
            throw new ParkingException(ErrorMessage.INVALID_COMMAND_ARGS);

        final Reservation reservation = parkingService.exit(args.get(0), args.get(1));
        outputMode.output(String.format("%s %.0f", reservation.getLot().toString(), reservation.getPrice()));
    }
}
