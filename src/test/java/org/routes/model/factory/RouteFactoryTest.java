package org.routes.model.factory;

import org.junit.jupiter.api.Test;
import org.routes.TestDataProvider;
import org.routes.io.CSVReader;
import org.routes.model.Route;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteFactoryTest {

    @Test
    void whenCreateThenReturnNewInstance() throws IOException {
        var routeFactory = new RouteFactory();
        var csvReader = new CSVReader(new FileReader("src/test/resources/io/input_routes.csv"));
        var record = csvReader.read().findFirst().get();

        Route route = routeFactory.create(record);

        assertEquals(TestDataProvider.getRoute(), route);
    }

    //    private List<Point> getExpectedPoints() {
//        return List.of(Point.builder()
//                .longitude(new BigDecimal("8.489074"))
//                .latitude(new BigDecimal("53.615707"))
//                .timestamp("1509423228430")
//                .speed(new BigDecimal("14.0")).build());
//    }
}