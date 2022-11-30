package org.routes.io.csv;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.routes.TestDataProvider.INPUT_PATH;

class CSVReaderTest {
    private FileReader fileReader;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        fileReader = new FileReader(INPUT_PATH);
    }

    @AfterEach
    void tearDown() throws IOException {
        if (fileReader != null) {
            fileReader.close();
        }
    }

    @Test
    void whenFileIsNullThenThrowException() {
        assertThrows(NullPointerException.class, () -> new CSVReader(null));
    }

    @Test
    void whenReadThenReadContentFromCSV() throws Exception {
        var csvReader = new CSVReader(fileReader);

        var records = csvReader.read();

        var recordsIterator = records.iterator();
        var firstRecord = recordsIterator.next();
        var secondRecord = recordsIterator.next();
        var expectedFirstInputRecord = getFirstInputRecord();
        var expectedSecondInputRecord = getSecondInputRecord();
        assertEquals(expectedFirstInputRecord, firstRecord.toMap());
        assertEquals(expectedSecondInputRecord, secondRecord.toMap());
        assertFalse(recordsIterator.hasNext());
    }

    private Map<String, String> getFirstInputRecord() {
        return Map.of(
                "id", "imo_9462794",
                "from_seq", "127",
                "to_seq", "128",
                "from_port", "DEBRV",
                "to_port", "DEHAM",
                "leg_duration", "36406308",
                "count", "135",
                "points", "[[8.489074, 53.615707, 1509423228430, 14.0]]"
        );
    }

    private Map<String, String> getSecondInputRecord() {
        return Map.of(
                "id", "imo_9454230",
                "from_seq", "196",
                "to_seq", "197",
                "from_port", "DEBRV",
                "to_port", "DEHAM",
                "leg_duration", "37389167",
                "count", "155",
                "points", "[[8.48967, 53.614338, 1507290057531, 12.5], [8.477636, 53.620094, 1507290217161, 12.5]]"
        );
    }
}