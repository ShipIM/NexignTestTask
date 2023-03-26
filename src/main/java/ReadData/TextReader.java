package ReadData;

import Entities.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Implementation of the FormatReader interface, for reading text files
 * of the format specified in the terms of reference
 */
public class TextReader implements FormatReader {
    /**
     * Auxiliary tool for reading data from a source
     */
    private final Scanner fileReader;

    /**
     * Due to the implementation of Scanner, it is also possible
     * to read from the console when passing a specific parameter
     *
     * @param fileReader Auxiliary tool for reading data from a source
     */
    public TextReader(Scanner fileReader) {
        this.fileReader = fileReader;
    }

    /**
     * Reads the data source line by line, parses it by separating commas and forms a new object of the Record class
     *
     * @return Collection of records extracted from the source as a result of its processing
     * @throws WrongFormatException If there are not enough arguments in the source string
     */
    @Override
    public List<Record> read() {
        List<Record> records = new ArrayList<>();
        String line;
        String[] parts;

        try {
            while (fileReader.hasNextLine()) {
                line = fileReader.nextLine();

                parts = line.split(",");

                if (parts.length > 5) throw new WrongFormatException("Too many parameters in source string");

                records.add(new Record(parts[0].trim(), parts[1].trim(),
                        parts[2].trim(), parts[3].trim(), parts[4].trim()));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongFormatException("Not enough parameters in source string");
        }

        return records;
    }
}
