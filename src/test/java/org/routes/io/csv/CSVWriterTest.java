package org.routes.io.csv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.routes.io.RouteHeaders;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CSVWriterTest {
    private final static String OUTPUT_FILE_NAME = "unit_test_output.csv";
    private final static String EXPECTED_OUTPUT_PATH = "src/test/resources/io/expected_output_routes.csv";
    @TempDir
    File tempDir;

    @Test
    void whenWriterIsNullThenThrowException() {
        assertThrows(NullPointerException.class, () -> new CSVWriter(null));
    }

    @Test
    void whenWriteThenCreateCSVWithContent() throws IOException {
        var newFilePath = Path.of(tempDir.getAbsolutePath(), OUTPUT_FILE_NAME).toString();
        var fileWriter = new FileWriter(newFilePath);
        var csvWriter = new CSVWriter(fileWriter);

        csvWriter.write(RouteHeaders.class, getRecord());
        fileWriter.close();

        var content = Files.readString(Path.of(newFilePath));
        var expectedContent = Files.readString(Path.of(EXPECTED_OUTPUT_PATH));
        assertEquals(expectedContent, content);
    }

    private List<String> getRecord() {
        return List.of("imo_9454230", "196", "197", "DEBRV", "DEHAM", "37389167", "155", "[[8.48967, 53.614338, 1507290057531, 12.5], [8.477636, 53.620094, 1507290217161, 12.5]]"
        );
    }
}