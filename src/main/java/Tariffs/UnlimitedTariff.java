package Tariffs;

import Entities.Call;

import java.util.List;

/**
 * A class that implements the logic of the unlimited tariff
 */
public class UnlimitedTariff implements Tariff {
    /**
     * Method for calculating the cost of all customers calls for the tariff period
     *
     * @param calls Collection of user calls for the tariff period
     * @return Total cost of calls
     */
    @Override
    public double calculate(List<Call> calls) {
        int sum = 100, current = 0, limit = 300, previous = limit;
        double cost;

        for (Call call : calls) {
            current += call.getDuration();

            if (current <= limit)
                cost = 0;
            else {
                cost = current - previous;
                previous = current;
            }

            call.setCost(cost);
            sum += cost;
        }

        return sum;
    }

    /**
     * An overridden method of the Object class
     *
     * @return Tariff index
     */
    @Override
    public String toString() {
        return "06";
    }
}