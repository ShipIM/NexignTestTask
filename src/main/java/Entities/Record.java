package Entities;

/**
 * A class for presenting a record in a more convenient form for further processing of the information contained in it.
 */
public class Record {
    /**
     * The string representation of the type of the call
     */
    private final String type;

    /**
     * The string representation of the customer's number
     */
    private final String number;

    /**
     * The string representation of the call start time in the format: "YYYYMMDDHH24MMSS"
     */
    private final String callStart;

    /**
     * The string representation of the call end time in the format: "YYYYMMDDHH24MMSS"
     */
    private final String callEnd;

    /**
     * The string representation of customer's tariff
     */
    private final String tariff;

    /**
     * @param type      String representation of the type of the call
     * @param number    String representation of the customer's number
     * @param callStart String representation of the call start time in the format: "YYYYMMDDHH24MMSS"
     * @param callEnd   String representation of the call end time in the format: "YYYYMMDDHH24MMSS"
     * @param tariff    String representation of customer's tariff
     */
    public Record(String type, String number, String callStart, String callEnd, String tariff) {
        this.type = type;
        this.number = number;
        this.callStart = callStart;
        this.callEnd = callEnd;
        this.tariff = tariff;
    }

    /**
     * @return String representation of the type of the call
     */
    public String getType() {
        return type;
    }

    /**
     * @return String representation of the customer's number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @return String representation of the call start time in the format: "YYYYMMDDHH24MMSS"
     */
    public String getCallStart() {
        return callStart;
    }

    /**
     * @return String representation of the call end time in the format: "YYYYMMDDHH24MMSS"
     */
    public String getCallEnd() {
        return callEnd;
    }

    /**
     * @return String representation of customer's tariff
     */
    public String getTariff() {
        return tariff;
    }
}
