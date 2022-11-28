package org.routes.model.factory;

import org.apache.commons.csv.CSVRecord;
import org.routes.io.RouteHeaders;
import org.routes.model.Route;

import java.time.Duration;

public class RouteFactory {
    public Route create(CSVRecord routeRecord) {
        return Route.builder()
                .id(routeRecord.get(RouteHeaders.id))
                .fromSeq(parseInt(routeRecord, RouteHeaders.from_seq))
                .toSeq(parseInt(routeRecord, RouteHeaders.to_seq))
                .fromPort(routeRecord.get(RouteHeaders.from_port))
                .toPort(routeRecord.get(RouteHeaders.to_port))
                .duration(parseDuration(routeRecord))
                .pointsCount(parseInt(routeRecord, RouteHeaders.count))
                .points(routeRecord.get(RouteHeaders.points))
                .build();
    }

    private Duration parseDuration(CSVRecord routeRecord) {
        var durationText = routeRecord.get(RouteHeaders.leg_duration);
        var durationMillis = Long.parseLong(durationText);
        return Duration.ofMillis(durationMillis);
    }

    private int parseInt(CSVRecord routeRecord, RouteHeaders header) {
        return Integer.parseInt(routeRecord.get(header));
    }

}
