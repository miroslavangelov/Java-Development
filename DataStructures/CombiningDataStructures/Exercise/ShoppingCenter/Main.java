package DataStructures.CombiningDataStructures.Exercise.ShoppingCenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfCommands = Integer.parseInt(reader.readLine());
        ShoppingCenter shoppingCenter = new ShoppingCenter();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < numberOfCommands; i++) {
            String[] commandData = reader.readLine().split(" ", 2);
            String command = commandData[0];

            switch (command) {
                case "AddProduct":
                    String[] commandParameters = commandData[1].split(";");
                    String productName = commandParameters[0];
                    double productPrice = Double.parseDouble(commandParameters[1]);
                    String productProducer = commandParameters[2];

                    result.append(shoppingCenter.addProduct(productName, productPrice, productProducer))
                        .append(System.lineSeparator());
                    break;
                case "FindProductsByName":
                    productName = commandData[1];
                    result.append(shoppingCenter.findProductsByName(productName));
                    break;
                case "FindProductsByProducer":
                    productProducer = commandData[1];
                    result.append(shoppingCenter.findProductsByProducer(productProducer));
                    break;
                case "FindProductsByPriceRange":
                    commandParameters = commandData[1].split(";");
                    double startPrice = Double.parseDouble(commandParameters[0]);
                    double endPrice = Double.parseDouble(commandParameters[1]);

                    result.append(shoppingCenter.findProductsByPriceRange(startPrice, endPrice));
                    break;
                case "DeleteProducts":
                    commandParameters = commandData[1].split(";");
                    if (commandParameters.length > 1) {
                        productName =  commandParameters[0];
                        productProducer = commandParameters[1];

                        result.append(shoppingCenter.deleteProductsByNameAndProducer(productName, productProducer))
                            .append(System.lineSeparator());
                    } else {
                        productProducer =  commandParameters[0];

                        result.append(shoppingCenter.deleteProductsByProducer(productProducer))
                                .append(System.lineSeparator());
                    }
                    break;
            }
        }

        System.out.print(result.toString());
    }
}
