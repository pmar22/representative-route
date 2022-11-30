# representative-route

Goal of the application is reducing input routes to the one representative route.

In order to reduce routes, CSVRoutesReducer executes the following steps:

- reads the routes from the .csv file
- finds a representative route by delivered algorithm
- writes the representative route to the output .csv file

In DurationMedianRouteFinder application provides with finding representative route by sorting input trips by leg_duration 
and selecting the median element.

Assumptions:

- Application saves output using the same headers, columns and separators as in DEBRV_DEHAM_historical_routes.csv
- Application throws RuntimeException when representative route cannot be founded (e.g. in case of input file doesn't
  contain any route)
- Application throws RuntimeException when occur some problems with writing or reading .csv file

Limitations:

- In Route, points field is of String type. It is necessary to create PointFactory (similar to RouteFactory).