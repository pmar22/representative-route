package org.routes.model.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.routes.TestDataProvider;
import org.routes.io.csv.CSVReader;
import org.routes.model.Route;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.routes.TestDataProvider.INPUT_PATH;

class RouteFactoryTest {
    private RouteFactory routeFactory;

    @BeforeEach
    void setUp() {
        routeFactory = new RouteFactory();
    }

    @Test
    void whenCreateThenReturnNewInstance() throws IOException {
        var csvReader = new CSVReader(new FileReader(INPUT_PATH));
        var record = csvReader.read().get(0);

        Route route = routeFactory.create(record);

        assertEquals(TestDataProvider.getRoute(), route);
    }

    @Test
    void whenCreateWithNullArgumentThenThrowException() {
        assertThrows(NullPointerException.class, () -> routeFactory.create(null));
    }
}