package org.routes.io.csv;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.routes.io.RouteHeaders;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CSVReader {
    @NonNull
    private final FileReader fileReader;

    public List<CSVRecord> read() throws IOException {
        var csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(RouteHeaders.class)
                .setSkipHeaderRecord(true)
                .build();
        return csvFormat.parse(fileReader).stream().collect(Collectors.toList());
    }
}
