import Entities.Call;
import Entities.Customer;
import Entities.Record;
import ReadData.FormatReader;
import ReadData.TextReader;
import ReadData.WrongFormatException;
import Tariffs.CommonTariff;
import Tariffs.MinuteTariff;
import Tariffs.Tariff;
import Tariffs.UnlimitedTariff;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * The main class of the entire application
 */
public class Application {
    /**
     * The input Point of the program also contains the main sequential processing logic
     *
     * @param args Arguments passed at startup
     */
    public static void main(String[] args) {
        // Read the console and process user input
        Scanner consoleReader = new Scanner(System.in);

        System.out.println("Specify the path to the file to read:");
        String fileName = consoleReader.nextLine();

        File file = new File(fileName);

        try (Scanner fileReader = new Scanner(file)) {
            // Read the contents of the file
            FormatReader formatReader = new TextReader(fileReader);

            List<Record> records = formatReader.read();

            Map<String, Customer> customers = new HashMap<>();
            Customer customer;

            try {
                /* Based on each record received, create a new Call class object and add it
                 to the set of the corresponding customer (if the customer does not exist yet - create) */
                for (Record record : records) {
                    Tariff tariff = getTariff(record.getTariff());
                    if (tariff == null) continue;

                    if (!customers.containsKey(record.getNumber())) {
                        customer = new Customer(record.getNumber(), tariff, new TreeSet<>());
                        customers.put(record.getNumber(), customer);
                    }

                    Call call = new Call(record.getType(), record.getCallStart(), record.getCallEnd());
                    customers.get(record.getNumber()).addNumber(call);
                }
            } catch (DateTimeParseException | IllegalArgumentException e) {
                // In case of bad parameters in the Record objects
                throw new WrongFormatException("The parameters in the lines do not match the expected ones");
            }

            // Generate and record reports in separate files in the "report" folder for each user
            File output = new File("reports/");

            if (output.mkdirs() || output.exists()) for (Map.Entry<String, Customer> entry : customers.entrySet()) {
                customer = entry.getValue();
                customer.calculate();

                output = new File("reports/" + customer.getNumber() + ".txt");
                try (PrintWriter writer = new PrintWriter(output, StandardCharsets.UTF_8)) {
                    writer.println(customer.display());
                }
            }
        } catch (IOException e) {
            // If the file with the entered name does not exist
            System.out.println("An error has occurred related to the input file.");
        } catch (WrongFormatException e) {
            // If the source format differs from the expected one
            System.out.println("An error occurred due to the discrepancy between "
                    + "the format of the input file and the expected one\n" + e.getMessage());
        }
    }

    /**
     * A simple factory method for instantiating an object of the class implementing the Tariff interface
     *
     * @param number Customer's tariff number
     * @return Object of the class of the corresponding tariff or null if no matches were found
     */
    public static Tariff getTariff(String number) {
        switch (number) {
            case "06":
                return new UnlimitedTariff();
            case "03":
                return new MinuteTariff();
            case "11":
                return new CommonTariff();
        }

        return null;
    }
}
