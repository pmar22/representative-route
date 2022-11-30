package org.routes.finder;

import lombok.NonNull;
import org.routes.model.Route;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class DurationMedianRouteFinder implements RepresentativeRouteFinder {
    @Override
    public Route find(@NonNull List<Route> routes) {
        if (routes.size() == 0) {
            throw new IllegalArgumentException("Cannot find representative route for empty routes argument");
        }
        var internalRoutes = new ArrayList<>(routes);
        var durationComparator = Comparator.comparing(Route::getDuration);

        internalRoutes.sort(durationComparator);
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
