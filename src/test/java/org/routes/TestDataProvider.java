package org.routes;

import org.routes.model.Route;

import java.time.Duration;
import java.util.List;

public class TestDataProvider {
    public static final String INPUT_PATH = "src/test/resources/io/input_routes.csv";

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

    public static List<String> getInputRecord() {
        return List.of("imo_9454230", "196", "197", "DEBRV", "DEHAM", "37389167", "155", "[[8.48967, 53.614338, 1507290057531, 12.5], [8.477636, 53.620094, 1507290217161, 12.5]]"
        );
    }
}
