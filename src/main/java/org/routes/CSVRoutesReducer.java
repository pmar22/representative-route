package org.routes;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.commons.csv.CSVRecord;
import org.routes.finder.RepresentativeRouteFinder;
import org.routes.io.RouteHeaders;
import org.routes.io.csv.CSVReader;
import org.routes.io.csv.CSVWriter;
import org.routes.model.Route;
import org.routes.model.factory.RouteFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public final class CSVRoutesReducer implements RoutesReducer {
    @NonNull
    private final CSVReader csvReader;
    @NonNull
    private final CSVWriter csvWriter;
    @NonNull
    private final RouteFactory routeFactory;
    @NonNull
    private final RepresentativeRouteFinder representativeRouteFinder;

    @Override
    public void reduce() {
        List<CSVRecord> csvRecords = readCSVRecords();
        var inputRoutes = csvRecords.stream()
                .map(routeFactory::create)
                .collect(Collectors.toList());
        var representativeRoute = representativeRouteFinder.find(inputRoutes);
        saveRoute(representativeRoute);
    }

    private List<CSVRecord> readCSVRecords() {
        try {
            return csvReader.read();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read input routes from CSV", e);
        }
    }

    private void saveRoute(Route route) {
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
