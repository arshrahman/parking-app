package output;

public class OutputMessages {
    public static String VEHICLE_NO = "{VehicleNo}";
    public static String LOT_ID = "{LotId}";
    public static String COMMAND = "{Command}";
    public static String MESSAGE = "{message}";

    public static String INVALID_FILE = "Oops! Unable to open file";
    public static String IO_EXCEPTION = "Oops! Unable to read file";
    public static String ALREADY_PARKED = VEHICLE_NO + " is already parked";
    public static String PARKING_FULL = "Parking full";
    public static String ACCEPT = "Accept " + LOT_ID;
    public static String REJECT = "Reject ";
    public static String ERROR_PROCESSING_COMMAND = "Error Processing " + COMMAND + " Command";
    public static String ERROR_PROCESSING_COMMAND_DETAIL = ERROR_PROCESSING_COMMAND + ". " + MESSAGE;

}
