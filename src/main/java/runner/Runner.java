package runner;

import org.routes.CSVRoutesReducer;
import org.routes.finder.DurationMedianRouteFinder;
import org.routes.io.csv.CSVReader;
import org.routes.io.csv.CSVWriter;
import org.routes.model.factory.RouteFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Runner {

    public static void main(String[] args) throws IOException {
        System.out.println("Start finding representative route");
        if (args.length < 2) {
            throw new RuntimeException("Please provide 2 input arguments: input file path and output file path");
        }
        var inputPath = args[0];
        var outputPath = args[1];

        var fileReader = new FileReader(inputPath);
        var csvReader = new CSVReader(fileReader);

        try (var fileWriter = new FileWriter(outputPath)) {
            var csvWriter = new CSVWriter(fileWriter);
            var routesReducer = new CSVRoutesReducer(csvReader, csvWriter, new RouteFactory(), new DurationMedianRouteFinder());
            routesReducer.reduce();
        }
        System.out.println("Representative route has been saved in the output file");
    }

}
