package Entities;

import Drawing.Display;
import Tariffs.Tariff;

import java.util.ArrayList;
import java.util.Set;

/**
 * A class for describing the entity and behavior of a customer
 */
public class Customer implements Display {
    /**
     * String representation of the customer's phone number
     */
    private final String number;

    /**
     * Customer's tariff
     */
    private final Tariff tariff;

    /**
     * Collection of all calls related to the customer
     */
    private final Set<Call> calls;

    /**
     * The resulting cost of the tariff period
     */
    private double cost;

    /**
     * @param number String representation of the customer's phone number
     * @param tariff Customer's tariff
     * @param calls  Collection of all calls related to the customer
     */
    public Customer(String number, Tariff tariff, Set<Call> calls) {
        this.number = number;
        this.tariff = tariff;
        this.calls = calls;
    }

    /**
     * @return String representation of the customer's phone number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param call New call to be added to the collection
     */
    public void addNumber(Call call) {
        this.calls.add(call);
    }

    /**
     * Method for calculating the total cost of the tariff period based on the tariff and the collection of calls,
     * as a result, the value of the cost field will be changed
     */
    public void calculate() {
        this.cost = this.tariff.calculate(new ArrayList<>(this.calls));
    }

    /**
     * Method for forming a text representation of the Customer object
     *
     * @return String representation of the current customer
     */
    @Override
    public String display() {
        StringBuilder multiplier = new StringBuilder();
        multiplier.append("-".repeat(76));

        StringBuilder builder = new StringBuilder();

        builder.append("Tariff index: ").append(tariff).append("\n").append(multiplier).append("\n")
                .append("Report for phone number ").append(number).append(":\n").append(multiplier).append("\n")
                .append(String.format("|%-10s|%-21s|%-21s|%-10s|%-8s|\n",
                        "Call type", "      Start time", "       End time", " Duration", "  Cost"));

        for (Call call : calls) {
            builder.append(call.display()).append("\n");
        }
        builder.append(multiplier).append("\n");

        builder.append(String.format("|%52s: |%18s |\n", "Total Cost", cost + " rubles"));
        builder.append(multiplier).append("\n");

        return builder.toString();
    }
}
