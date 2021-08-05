package command;

import exception.ErrorMessage;
import exception.ParkingException;
import output.OutputMode;
import service.ParkingService;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Command> commandMap = new HashMap<>();
    private final OutputMode outputMode;

    public CommandFactory(ParkingService parkingService, OutputMode outputMode) {
        this.outputMode = outputMode;
        commandMap.put(InitCommand.COMMAND, new InitCommand(parkingService, outputMode));
        commandMap.put(EnterCommand.COMMAND, new EnterCommand(parkingService, outputMode));
        commandMap.put(ExitCommand.COMMAND, new ExitCommand(parkingService, outputMode));
    }

    public Command getCommand(String command) throws ParkingException {
        if (commandMap.containsKey(command))
            return commandMap.get(command);

        throw new ParkingException(ErrorMessage.INVALID_COMMAND);
    }

    public OutputMode getOutputMode() {
        return this.outputMode;
    }

}
