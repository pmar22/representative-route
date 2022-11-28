package org.routes.io;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.QuoteMode;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CSVWriter {
    private static final String RECORD_SEPARATOR = "\r\r";
    private static final char QUOTE_CHARACTER = '"';
    private final FileWriter fileWriter;

    public CSVWriter(FileWriter fileWriter) {
        Objects.requireNonNull(fileWriter);
        this.fileWriter = fileWriter;
    }

    public void write(List<String> record) throws IOException {
        var csvFormat = CSVFormat.EXCEL
                .builder()
                .setRecordSeparator(RECORD_SEPARATOR)
                .setQuote(QUOTE_CHARACTER)
                .setQuoteMode(QuoteMode.ALL)
                .setHeader(RouteHeaders.class)
                .build();
        var csvPrinter = csvFormat.print(fileWriter);

        csvPrinter.printRecord(record);
        csvPrinter.flush();
    }
}
