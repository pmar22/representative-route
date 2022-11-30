package org.routes.integration_test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.routes.CSVRoutesReducer;
import org.routes.finder.DurationMedianRouteFinder;
import org.routes.io.csv.CSVReader;
import org.routes.io.csv.CSVWriter;
import org.routes.model.factory.RouteFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVRoutesReducerIntegrationTest {
    private final static String INPUT_PATH = "src/test/resources/integration/DEBRV_DEHAM_historical_routes.csv";
    private final static String EXPECTED_OUTPUT_PATH = "src/test/resources/integration/expected_output.csv";
    private final static String OUTPUT_FILE_NAME = "output.csv";
    @TempDir
    private File tempDir;

    @Test
    void whenReduceThenGenerateRepresentativeRoute() throws IOException {
        var fileReader = new FileReader(INPUT_PATH);
        var csvReader = new CSVReader(fileReader);
        var outputPath = Path.of(tempDir.getAbsolutePath(), OUTPUT_FILE_NAME).toString();

        try (var fileWriter = new FileWriter(outputPath)) {
            var csvWriter = new CSVWriter(fileWriter);
            var routesReducer = new CSVRoutesReducer(csvReader, csvWriter, new RouteFactory(), new DurationMedianRouteFinder());
            routesReducer.reduce();
        }

        var content = Files.readString(Path.of(outputPath));
        var expectedContent = Files.readString(Path.of(EXPECTED_OUTPUT_PATH));
        assertEquals(expectedContent, content);
    }

}