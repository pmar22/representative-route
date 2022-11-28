package org.routes;

import org.routes.model.Route;

import java.time.Duration;

public class TestDataProvider {

    public static Route getRoute() {
        return Route.builder()
                .id("imo_9462794")
                .fromSeq(127)
                .toSeq(128)
                .fromPort("DEBRV")
                .toPort("DEHAM")
                .duration(Duration.ofMillis(36406308))
                .pointsCount(135)
                .points("[[8.489074, 53.615707, 1509423228430, 14.0]]")
                .build();
    }
}
