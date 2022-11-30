# representative-route

Goal of the application is reducing input routes to the one representative route.

In order to reduce routes, CSVRoutesReducer executes the following steps:
- reading routes from the .csv file
- finds the representative route by delivered algorithm
- writes representative route to the output .csv file

In DurationMedianRouteFinder application provides with finding representative route by selecting the median of trip duration from input routes

Assumptions:
- Application saves output using the same headers, columns and separators as in DEBRV_DEHAM_historical_routes.csv
- Application throws RuntimeException, when representative route cannot be founded (e.g. in case of input file doesn't contain any route)

Limitations:
- In Route, points field is String type. It is necessary to create PointFactory (similar to RouteFactory).