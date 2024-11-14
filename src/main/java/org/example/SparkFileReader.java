package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Row;

public class SparkFileReader {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("Spark File Reader")
                .config("spark.master", "local")
                .getOrCreate();

        // Adjust the path as needed, or pass it as an argument
        String filePath = args.length > 0 ? args[0] : "src/main/resources/sample_data.csv";

        // Read CSV file
        Dataset<Row> df = spark.read().format("csv")
                .option("header", "true")
                .option("inferSchema", "true")
                .load(filePath);

        // Show the data
        df.show();

        spark.stop();
    }
}

