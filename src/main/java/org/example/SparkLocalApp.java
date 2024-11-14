package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

public class SparkLocalApp {
    public static void main(String[] args) {
        // Initialize Spark Session
        SparkSession spark = SparkSession.builder()
                .appName("SparkLocalApp")
                .master("local[*]") // Run Spark locally
                .getOrCreate();

        // Define the path to the JSON file
        String jsonFilePath = "people.json";

        // Read the JSON file
        Dataset<Row> df = spark.read().option("multiLine", "true").json(jsonFilePath);

        // Show the DataFrame content
        //df.show(false);

        // Flatten the "people" array using explode
        Dataset<Row> explodedDf = df.select(functions.explode(df.col("people")).alias("person"));

        // Show the exploded DataFrame
        //explodedDf.show(false);

        // Access fields inside the nested "person" column
        explodedDf.select("person.name", "person.age", "person.city").show(false);

        // Stop the Spark session
        spark.stop();
    }
}
