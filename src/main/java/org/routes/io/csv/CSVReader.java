package org.routes.io.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.routes.io.RouteHeaders;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

public class CSVReader {
    private final FileReader fileReader;

    public CSVReader(FileReader fileReader) {
        Objects.requireNonNull(fileReader);
        this.fileReader = fileReader;
    }

    public Stream<CSVRecord> read() throws IOException {
        var csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(RouteHeaders.class)
                .setSkipHeaderRecord(true)
                .build();
        return csvFormat.parse(fileReader).stream();
    }
}
