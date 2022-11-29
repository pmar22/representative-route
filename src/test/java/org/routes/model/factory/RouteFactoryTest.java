package org.routes.model.factory;

import org.junit.jupiter.api.Test;
import org.routes.TestDataProvider;
import org.routes.io.csv.CSVReader;
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

}