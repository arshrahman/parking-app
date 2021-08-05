package input;

import command.CommandFactory;
import command.CommandParser;
import command.InitCommand;
import exception.ParkingException;
import output.OutputMessages;
import output.OutputMode;

import java.io.*;

public class FileInput {
    private final String filepath;
    private final CommandFactory commandFactory;
    private final OutputMode outputMode;

    public FileInput(String filepath, CommandFactory commandFactory) {
        this.filepath = filepath;
        this.commandFactory = commandFactory;
        this.outputMode = commandFactory.getOutputMode();
    }

    public void run() throws ParkingException {
        final File file = new File(filepath);
        final BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
            String input = reader.readLine();
            CommandParser commandParser = new CommandParser(InitCommand.COMMAND, input);
            this.commandFactory.getCommand(commandParser.getCommand()).execute(commandParser.getArguments());

            input = reader.readLine();
            while (input != null) {
                try {
                    commandParser = new CommandParser(input);
                    this.commandFactory.getCommand(commandParser.getCommand()).execute(commandParser.getArguments());
                } catch (ParkingException e) {
                    final String message = OutputMessages.ERROR_PROCESSING_COMMAND_DETAIL
                                    .replace(OutputMessages.COMMAND, commandParser.getCommand())
                                    .replace(OutputMessages.MESSAGE, e.getMessage());
                    outputMode.output(message);
                }

                input = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            outputMode.output(OutputMessages.INVALID_FILE);
        } catch (IOException e) {
            outputMode.output(OutputMessages.IO_EXCEPTION);
        }
    }

}
