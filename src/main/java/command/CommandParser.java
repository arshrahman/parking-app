package command;

import exception.ErrorMessage;
import exception.ParkingException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandParser {

    private static final String SPLIT_BY = "\\s+";

    private String command;
    private final List<String> arguments;

    public CommandParser(String command, String line) throws ParkingException {
        this(line, false);
        this.command = command;
    }

    public CommandParser(String line) throws ParkingException {
        this(line, true);
    }


    private CommandParser(String line, boolean withCommand) throws ParkingException {
        if (line == null) throw new ParkingException(ErrorMessage.INVALID_COMMAND);

        final List<String> inputArguments = Arrays.stream(line.trim().toUpperCase().split(SPLIT_BY))
                .filter(arg -> arg.length() > 0)
                .collect(Collectors.toList());

        if (withCommand) {
            this.command = inputArguments.get(0);
            inputArguments.remove(0);
        }

        this.arguments = inputArguments;
    }

    public String getCommand() {
        return command;
    }


    public List<String> getArguments() {
        return arguments;
    }

}
