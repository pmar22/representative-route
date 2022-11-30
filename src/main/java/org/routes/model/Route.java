package org.routes.model;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Data
@Builder
public final class Route {
    private final String id;
    private final int fromSeq;
    private final int toSeq;
    private final String fromPort;
    private final String toPort;
    private final Duration duration;
    private final int pointsCount;
    private final String points;
}
