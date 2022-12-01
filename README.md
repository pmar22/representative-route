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
- Application throws IOException when occur some problems with writing or reading .csv file

Limitations:

- In Route, points field is of String type. It is necessary to create PointFactory (similar to RouteFactory).

How to run application:

1. mvn clean install - it will build and generate jar artifacts
2. java -cp target/representative-route-1.0-SNAPSHOT-jar-with-dependencies.jar runner.Runner {input_routes_csv_path} {output_routes_csv_path}

For example execute in the main project directory:

java -cp target/representative-route-1.0-SNAPSHOT-jar-with-dependencies.jar runner.Runner src/test/resources/integration/DEBRV_DEHAM_historical_routes.csv ./output.csv

File output.csv should contain the representative path