package org.routes;

import lombok.AllArgsConstructor;
import org.routes.finder.RepresentativeRouteFinder;
import org.routes.io.RouteWriter;
import org.routes.io.csv.CSVReader;
import org.routes.model.factory.RouteFactory;

import java.io.IOException;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RoutesReducer {
    private final RouteWriter routeWriter;
    private final CSVReader csvReader;
    private final RouteFactory routeFactory;
    private final RepresentativeRouteFinder representativeRouteFinder;

    public void reduce() throws IOException {
        var recordsStream = csvReader.read();
        var inputRoutes = recordsStream.map(routeFactory::create).collect(Collectors.toList());
        var representativeRoute = representativeRouteFinder.find(inputRoutes);
        routeWriter.save(representativeRoute);
    }
}
