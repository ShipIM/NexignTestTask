package Tariffs;

import Entities.Call;

import java.util.List;

/**
 * Interface describing methods for calculating the cost of subscriber calls
 */
public interface Tariff {
    /**
     * Method for calculating the cost of all customers calls for the tariff period
     *
     * @param calls Collection of customer calls for the tariff period
     * @return Total cost of calls
     */
    double calculate(List<Call> calls);
}
