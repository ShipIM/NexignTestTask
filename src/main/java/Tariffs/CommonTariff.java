package Tariffs;

import Entities.Call;

import java.util.List;

/**
 * A class that implements the logic of the common tariff
 */
public class CommonTariff implements Tariff {
    /**
     * Method for calculating the cost of all customers calls for the tariff period
     *
     * @param calls Collection of customer calls for the tariff period
     * @return Total cost of calls
     */
    @Override
    public double calculate(List<Call> calls) {
        int current = 0, limit = 100, previous;
        double sum = 0, cost;

        for (Call call : calls)
            if (call.getType() == Call.Type.T_01) {
                previous = current;
                current += call.getDuration();

                if (current <= limit)
                    cost = (current - previous) * 0.5;
                else {
                    if (previous < limit)
                        cost = (limit - previous + 1) * 0.5 + (current - limit) * 1.5;
                    else
                        cost = (current - previous) * 1.5;
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
        return "11";
    }
}
