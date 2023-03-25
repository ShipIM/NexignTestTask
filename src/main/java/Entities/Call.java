package Entities;

import Drawing.Display;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class for describing the entity and behavior of a call
 */
public class Call implements Comparable<Call>, Display {
    /**
     * String constant-template for formatting date and time
     */
    private final String STRING_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Call start time
     */
    private final LocalDateTime start;

    /**
     * Call end time
     */
    private final LocalDateTime end;

    /**
     * The type of the call, an instance of the Type enumeration
     */
    private final Type type;

    /**
     * The cost of the call
     */
    private double cost;

    /**
     * @param type  String representation of the call type
     * @param start String representation of the call start time in the format: "YYYYMMDDHH24MMSS"
     * @param end   String representation of the call end time in the format: "YYYYMMDDHH24MMSS"
     */
    public Call(String type, String start, String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
        this.type = Type.valueOf("T_" + type);
    }

    /**
     * @return An instance of the Type enumeration, that is set for current call
     */
    public Type getType() {
        return type;
    }

    /**
     * @return Duration of current call
     */
    public long getDuration() {
        Duration duration = Duration.between(this.start, this.end);

        return duration.toMinutes();
    }

    /**
     * @return String representation of the call start time in the format: "yyyy-MM-dd HH:mm:ss"
     */
    public String getStringifyStart() {
        return this.start.format(DateTimeFormatter.ofPattern(STRING_PATTERN));
    }

    /**
     * @return String representation of the call end time in the format: "yyyy-MM-dd HH:mm:ss"
     */
    public String getStringifyEnd() {
        return this.end.format(DateTimeFormatter.ofPattern(STRING_PATTERN));
    }

    /**
     * @return String representation of the call end time in the format: HH:mm:ss"
     */
    public String getStringifyDuration() {
        Duration duration = Duration.between(this.start, this.end);

        long seconds = duration.getSeconds();
        long HH = seconds / 3600;
        long MM = (seconds % 3600) / 60;
        long SS = seconds % 60;

        return String.format("%02d:%02d:%02d", HH, MM, SS);
    }

    /**
     * @param cost Double value that will be set as the cost of the call
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @param compare Object to be compared.
     * @return Result of comparing two Call objects, in fact,
     * it is equal to the result of comparing the start time of calls
     */
    @Override
    public int compareTo(Call compare) {
        return start.compareTo(compare.start);
    }

    /**
     * Method for forming a text representation of the Call object
     *
     * @return String representation of the current call
     */
    @Override
    public String display() {
        return String.format("|%-10s|%-21s|%-21s| %s |%-8s|", "    " + this.type.code, " " + this.getStringifyStart(),
                " " + this.getStringifyEnd(), this.getStringifyDuration(),
                "  " + String.format("%.2f", this.cost).replace(",", "."));
    }

    /**
     * An enumeration containing all existing call types
     */
    public enum Type {
        /**
         * Code for incoming calls
         */
        T_02("02"),

        /**
         * Code for outgoing calls
         */
        T_01("01");

        /**
         * Call code
         */
        private final String code;

        /**
         * @param code String representation of Call type
         */
        Type(String code) {
            this.code = code;
        }
    }
}
