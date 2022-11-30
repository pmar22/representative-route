package org.routes.io.csv;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.QuoteMode;
import org.routes.io.RouteHeaders;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@RequiredArgsConstructor
public class CSVWriter {
    private static final String RECORD_SEPARATOR = "\r\r";
    private static final char QUOTE_CHARACTER = '"';
    @NonNull
    private final Writer writer;

    public void write(Class<RouteHeaders> headers, List<String> record) throws IOException {
        var csvFormat = CSVFormat.EXCEL
                .builder()
                .setRecordSeparator(RECORD_SEPARATOR)
                .setQuote(QUOTE_CHARACTER)
                .setQuoteMode(QuoteMode.ALL)
                .setHeader(headers)
                .build();
        var csvPrinter = csvFormat.print(writer);

        csvPrinter.printRecord(record);
        csvPrinter.flush();
    }
}
