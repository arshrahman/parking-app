package exception;

public enum ErrorMessage {
    INVALID_INPUT_FILE("Please pass in input file"),
    INVALID_COMMAND("Please pass in valid command with arguments"),
    INVALID_COMMAND_ARGS("Please pass in valid command arguments"),
    INVALID_PARKING_LOT_SIZE("Please pass in valid parking lot size"),
    PROCESSING_ERROR("Processing error"),
    NOT_PARKED("Vehicle not parked in the parking lot"),
    INVALID_DATE("Vehicle enter and exit date should be valid"),
    INVALID_VEHICLE("Vehicle type is invalid");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
