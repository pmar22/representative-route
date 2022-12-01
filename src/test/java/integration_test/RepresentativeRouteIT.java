package integration_test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import runner.Runner;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RepresentativeRouteIT {
    private final static String INPUT_PATH = "src/test/resources/integration/DEBRV_DEHAM_historical_routes.csv";
    private final static String EXPECTED_OUTPUT_PATH = "src/test/resources/integration/expected_output.csv";
    private final static String OUTPUT_FILE_NAME = "output.csv";
    @TempDir
    private File tempDir;

    @Test
    void whenMainThenGenerateRepresentativeRoute() throws Exception {
        var outputPath = Path.of(tempDir.getAbsolutePath(), OUTPUT_FILE_NAME).toString();
        var inputArguments = new String[]{INPUT_PATH, outputPath};

        Runner.main(inputArguments);

        var content = Files.readString(Path.of(outputPath));
        var expectedContent = Files.readString(Path.of(EXPECTED_OUTPUT_PATH));
        assertEquals(expectedContent, content);
    }

    @Test
    void whenArgumentsNotProvidedThenThrowException() {
        assertThrows(RuntimeException.class, () -> Runner.main(new String[0]));
    }
}