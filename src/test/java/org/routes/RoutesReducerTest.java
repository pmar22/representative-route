package org.routes;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.routes.finder.RepresentativeRouteFinder;
import org.routes.io.csv.CSVReader;
import org.routes.io.csv.CSVWriter;
import org.routes.model.Route;
import org.routes.model.factory.RouteFactory;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.routes.TestDataProvider.getInputRecord;
import static org.routes.TestDataProvider.getRoute;

@ExtendWith(MockitoExtension.class)
class RoutesReducerTest {
    @Mock
    private CSVWriter csvWriter;
    @Mock
    private CSVReader csvReader;
    @Mock
    private RouteFactory routeFactory;
    @Mock
    private RepresentativeRouteFinder representativeRouteFinder;
    @InjectMocks
    private RoutesReducer routesReducer;

    @Test
    void whenReduceThenCallRouteFactory() throws IOException {
        var records = getCSVRecords();
        var firstRecord = records.get(0);
        when(csvReader.read()).thenReturn(records);
        when(routeFactory.create(firstRecord)).thenReturn(Route.builder().build());
        when(representativeRouteFinder.find(any())).thenReturn(getRoute());

        routesReducer.reduce();

        verify(routeFactory).create(records.get(0));
    }

    @Test
    void whenReduceThenCallFinder() throws IOException {
        var records = getCSVRecords();
        var firstRecord = records.get(0);
        var route = Route.builder().id("12abc").build();
        when(csvReader.read()).thenReturn(records);
        when(routeFactory.create(firstRecord)).thenReturn(route);
        when(representativeRouteFinder.find(any())).thenReturn(getRoute());

        routesReducer.reduce();

        verify(representativeRouteFinder).find(List.of(route));
    }

    @Test
    void whenNullCSVReaderThenThrowException() {
        assertThrows(NullPointerException.class, () -> new RoutesReducer(null, csvWriter, routeFactory, representativeRouteFinder));
    }

    @Test
    void whenNullCSVWriterThenThrowException() {
        assertThrows(NullPointerException.class, () -> new RoutesReducer(csvReader, null, routeFactory, representativeRouteFinder));
    }

    @Test
    void whenNullRouteFactoryThenThrowException() {
        assertThrows(NullPointerException.class, () -> new RoutesReducer(csvReader, csvWriter, null, representativeRouteFinder));
    }

    @Test
    void whenNullRouteFinderThenThrowException() {
        assertThrows(NullPointerException.class, () -> new RoutesReducer(csvReader, csvWriter, routeFactory, null));
    }

    @Test
    void whenReduceThenReadFromCSV() throws IOException {
        when(representativeRouteFinder.find(any())).thenReturn(getRoute());
        routesReducer.reduce();

        verify(csvReader).read();
    }

    @Test
    void whenInputIOExceptionThenThrowRuntime() throws IOException {
        when(csvReader.read()).thenThrow(IOException.class);

        assertThrows(RuntimeException.class, () -> routesReducer.reduce());
    }

    @Test
    void whenOutputIOExceptionThenThrowRuntime() {
        assertThrows(RuntimeException.class, () -> routesReducer.reduce());
    }

    private List<CSVRecord> getCSVRecords() throws IOException {
        try (var parser = CSVParser.parse(String.join(",", getInputRecord()), CSVFormat.DEFAULT)) {
            return parser.getRecords();
        }
    }

}