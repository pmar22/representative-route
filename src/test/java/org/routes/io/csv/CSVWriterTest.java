package org.routes.io.csv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.routes.io.RouteHeaders;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.routes.TestDataProvider.getInputRecord;

class CSVWriterTest {
    private final static String OUTPUT_FILE_NAME = "unit_test_output.csv";
    private final static String EXPECTED_OUTPUT_PATH = "src/test/resources/io/expected_output_routes.csv";
    @TempDir
    private File tempDir;

    @Test
    void whenWriterIsNullThenThrowException() {
        assertThrows(NullPointerException.class, () -> new CSVWriter(null));
    }

    @Test
    void whenWriteThenCreateCSVWithContent() throws IOException {
        var newFilePath = Path.of(tempDir.getAbsolutePath(), OUTPUT_FILE_NAME).toString();
        var fileWriter = new FileWriter(newFilePath);
        var csvWriter = new CSVWriter(fileWriter);

        csvWriter.write(RouteHeaders.class, getInputRecord());
        fileWriter.close();

        var content = Files.readString(Path.of(newFilePath));
        var expectedContent = Files.readString(Path.of(EXPECTED_OUTPUT_PATH));
        assertEquals(expectedContent, content);
    }

}