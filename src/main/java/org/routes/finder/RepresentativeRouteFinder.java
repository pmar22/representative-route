package org.routes.finder;

import org.routes.model.Route;

import java.util.List;

public interface RepresentativeRouteFinder {
    Route find(List<Route> routes);
}
