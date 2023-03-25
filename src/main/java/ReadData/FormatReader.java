package ReadData;

import Entities.Record;

import java.util.List;

/**
 * An interface describing methods for reading and processing various source data formats
 */
public interface FormatReader {
    /**
     * Method for processing data from the source
     *
     * @return Collection of records extracted from the source as a result of its processing
     */
    List<Record> read();
}
