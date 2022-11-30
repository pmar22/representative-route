package org.routes.io;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.routes.io.csv.CSVWriter;
import org.routes.model.Route;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class RouteWriter {
    @NonNull
    private final CSVWriter csvWriter;

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
