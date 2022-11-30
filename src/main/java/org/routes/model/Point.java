package org.routes.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public final class Point {
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final String timestamp;
    private final BigDecimal speed;
}
