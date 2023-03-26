package Tariffs;

import Entities.Call;

import java.util.List;

/**
 * A class that implements the logic of the per-minute tariff
 */
public class MinuteTariff implements Tariff {
    /**
     * Method for calculating the cost of all customers calls for the tariff period
     *
     * @param calls Collection of customer calls for the tariff period
     * @return Total cost of calls
     */
    @Override
    public double calculate(List<Call> calls) {
        double current, sum = 0;

        for (Call call : calls) {
            current = call.getDuration() * 1.5;

            call.setCost(current);
            sum += current;
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
        return "03";
    }
}
