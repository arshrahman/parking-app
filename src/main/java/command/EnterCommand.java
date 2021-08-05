package command;

import exception.ErrorMessage;
import exception.ParkingException;
import model.Reservation;
import output.OutputMessages;
import output.OutputMode;
import service.ParkingService;

import java.util.List;

public class EnterCommand extends Command{

    public static String COMMAND = "ENTER";
    public static int COMMAND_ARGS = 3;

    EnterCommand(ParkingService parkingService, OutputMode outputMode) {
        super(parkingService, outputMode);
    }

    @Override
    public void execute(List<String> args) throws ParkingException {
        if (args.size() != COMMAND_ARGS)
            throw new ParkingException(ErrorMessage.INVALID_COMMAND_ARGS);

        final Reservation reservation = parkingService.enter(args.get(0), args.get(1), args.get(2));
        if (reservation.isReserved())
            outputMode.output(OutputMessages.ACCEPT.replace(OutputMessages.LOT_ID, reservation.getLot().toString()));
        else
            outputMode.output(OutputMessages.REJECT + reservation.getRemarks());


    }
}
