package output;

public class ConsoleOutput extends OutputMode {

    @Override
    public void output(String message) {
        System.out.println(message);
    }
}
