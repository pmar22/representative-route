package org.routes.finder;

import org.junit.jupiter.api.Test;
import org.routes.model.Route;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DurationMedianRouteFinderTest {

    @Test
    void whenNullArgumentThenThrowNullPointerException() {
        var finder = new DurationMedianRouteFinder();

        assertThrows(NullPointerException.class, () -> finder.find(null));
    }

    @Test
    void whenZeroRoutesThenThrowException() {
        var finder = new DurationMedianRouteFinder();

        assertThrows(IllegalArgumentException.class, () -> finder.find(List.of()));
    }

    @Test
    void whenFindForOneRouteThenReturnIt() {
        var finder = new DurationMedianRouteFinder();

        Route representativeRoute = finder.find(List.of(getRouteWithDuration(100L)));

        assertEquals(getRouteWithDuration(100L), representativeRoute);
    }

    @Test
    void whenFindWithOddRoutesThenFindRepresentativeRoute() {
        var finder = new DurationMedianRouteFinder();

        Route representativeRoute = finder.find(getOddRoutes());

        assertEquals(getRouteWithDuration(50L), representativeRoute);
    }

    @Test
    void whenFindWithEvenRoutesThenFindRepresentativeRoute() {
        var finder = new DurationMedianRouteFinder();

        Route representativeRoute = finder.find(getEvenRoutes());

        assertEquals(getRouteWithDuration(25L), representativeRoute);
    }

    public static List<Route> getOddRoutes() {
        return List.of(getRouteWithDuration(100L), getRouteWithDuration(50L), getRouteWithDuration(1L));
    }

    public static List<Route> getEvenRoutes() {
        return List.of(getRouteWithDuration(100L), getRouteWithDuration(25L), getRouteWithDuration(50L), getRouteWithDuration(1L));
    }

    private static Route getRouteWithDuration(long millis) {
        return Route.builder().duration(Duration.ofMillis(millis)).build();
    }

}