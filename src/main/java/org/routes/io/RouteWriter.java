package org.routes.io;

import org.routes.io.csv.CSVWriter;
import org.routes.model.Route;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class RouteWriter {
    private final CSVWriter csvWriter;

    public RouteWriter(CSVWriter csvWriter) {
        Objects.requireNonNull(csvWriter);
        this.csvWriter = csvWriter;
    }

    public void save(Route route) {
        try {
            csvWriter.write(RouteHeaders.class, List.of(route.getId(), getString(route.getFromSeq()), getString(route.getToSeq()), route.getFromPort(), route.getToPort(), getDurationMilliseconds(route), getString(route.getPointsCount()), route.getPoints()));
        } catch (IOException e) {
            throw new RuntimeException("Cannot write route to CSV file", e);
        }
    }

    private String getString(int route) {
        return String.valueOf(route);
    }

    private String getDurationMilliseconds(Route route) {
        return String.valueOf(route.getDuration().toMillis());
    }
}
