package input;

import output.OutputMessages;
import output.OutputMode;

import java.io.*;

public class FileInput {
    private String filepath;
    private OutputMode outputMode;

    public FileInput(String filepath, OutputMode outputMode) {
        this.filepath = filepath;
        this.outputMode = outputMode;
    }

    public void process() {
        final File file = new File(filepath);
        final BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
            String input = reader.readLine();

        } catch (FileNotFoundException e) {
            outputMode.output(OutputMessages.INVALID_FILE);
        } catch (IOException e) {
            outputMode.output(OutputMessages.IO_EXCEPTION);
        }

    }


}
