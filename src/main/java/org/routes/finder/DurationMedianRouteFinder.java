package org.routes.finder;

import org.routes.model.Route;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class DurationMedianRouteFinder implements RepresentativeRouteFinder {
    @Override
    public Route find(List<Route> routes) {
        Objects.requireNonNull(routes);
        if (routes.size() == 0) {
            throw new IllegalArgumentException("Cannot find representative route for empty routes argument");
        }
        var internalRoutes = new ArrayList<>(routes);
        var comparator = Comparator.comparing(Route::getDuration);

        internalRoutes.sort(comparator);
        return getMedian(internalRoutes);
    }

    private Route getMedian(List<Route> sortedRoutes) {
        var size = sortedRoutes.size();
        var isOddNumber = size % 2 == 1;
        if (isOddNumber) {
            return sortedRoutes.get((size + 1) / 2 - 1);
        } else {
            return sortedRoutes.get(size / 2 - 1);
        }
    }
}
