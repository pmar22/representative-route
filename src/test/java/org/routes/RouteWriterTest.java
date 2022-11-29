package org.routes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.routes.io.CSVWriter;
import org.routes.io.RouteHeaders;
import org.routes.model.Route;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.routes.TestDataProvider.getRoute;

@ExtendWith(MockitoExtension.class)
class RouteWriterTest {
    @Mock
    private CSVWriter csvWriter;

    @Test
    void whenNullArgumentThenThrowException() {
        assertThrows(NullPointerException.class, () -> new RouteWriter(null));
    }

    @Test
    void whenSaveThenSaveObjectToFile() throws IOException {
        var routesService = new RouteWriter(csvWriter);
        var route = getRoute();

        routesService.save(route);

        verify(csvWriter).write(RouteHeaders.class, getExpectedCSVFields(route));
    }

    @Test
    void whenCSVWriterIOExceptionThenThrowRuntimeException() throws IOException {
        var routesService = new RouteWriter(csvWriter);
        var route = getRoute();
        doThrow(new IOException()).when(csvWriter).write(RouteHeaders.class, getExpectedCSVFields(route));

        assertThrows(RuntimeException.class, () -> routesService.save(route));
    }

    private List<String> getExpectedCSVFields(Route route) {
        return List.of(
                route.getId(),
                String.valueOf(route.getFromSeq()),
                String.valueOf(route.getToSeq()),
                route.getFromPort(),
                route.getToPort(),
                String.valueOf(route.getDuration().toMillis()),
                String.valueOf(route.getPointsCount()),
                route.getPoints());
    }
}