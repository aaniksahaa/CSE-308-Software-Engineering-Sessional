package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StockParser {
    public static List<StockDTO> parseStockFile() throws IOException {
        String filePath = "src/init_stocks.txt";
        List<StockDTO> stocks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\s+");

                if (tokens.length == 3) {
                    String stockName = tokens[0];
                    int count = Integer.parseInt(tokens[1]);
                    double price = Double.parseDouble(tokens[2]);

                    stocks.add(new StockDTO(stockName, count, price));
                } else {
                    System.err.println("Invalid input: " + line);
                }
            }
        }

        return stocks;
    }
    // this function is just for testing
    public static void main(String[] args) {
        String filePath = "src/init_stocks.txt";

        try {
            List<StockDTO> stocks = parseStockFile();

            for (StockDTO stock : stocks) {
                System.out.println(stock);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

