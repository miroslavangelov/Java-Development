package JavaOOPAdvanced.ObjectCommunicationAndEvents.DependencyInversion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        PrimitiveCalculator primitiveCalculator = new PrimitiveCalculator();

        while (!"End".equals(currentLine)) {
            String[] data = currentLine.split("\\s+");

            if (data[0].equals("mode")) {
                String[] strategyData = currentLine.split("\\s+");
                char operator = strategyData[1].toCharArray()[0];

                primitiveCalculator.changeStrategy(operator);
            } else {
                int firstOperand = Integer.parseInt(data[0]);
                int secondOperand = Integer.parseInt(data[1]);

                System.out.println(primitiveCalculator.performCalculation(firstOperand, secondOperand));
            }

            currentLine = reader.readLine();
        }
    }
}
