package exception;

public class ParkingException extends Exception {
    public ParkingException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
