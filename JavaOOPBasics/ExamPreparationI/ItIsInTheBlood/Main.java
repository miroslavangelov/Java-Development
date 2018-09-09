package JavaOOPBasics.ExamPreparationI.ItIsInTheBlood;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        HealthManager healthManager = new HealthManager();
        StringBuilder result = new StringBuilder();

        while (!"BEER IS COMING".equals(currentLine)) {
            String[] commandData = currentLine.split("\\s+");
            String commandType = commandData[0];
            String name = commandData[1];

            switch (commandType) {
                case "checkCondition":
                    result.append(healthManager.checkCondition(name));
                    break;
                case "createOrganism":
                    result.append(healthManager.createOrganism(name));
                    break;
                case "addCluster":
                    String clusterId = commandData[2];
                    int clusterRows = Integer.parseInt(commandData[3]);
                    int clusterCols = Integer.parseInt(commandData[4]);
                    result.append(healthManager.addCluster(name, clusterId, clusterRows, clusterCols));
                    break;
                case "addCell":
                    clusterId = commandData[2];
                    String cellType = commandData[3];
                    String cellId = commandData[4];
                    int health = Integer.parseInt(commandData[5]);
                    int positionRow = Integer.parseInt(commandData[6]);
                    int positionCol = Integer.parseInt(commandData[7]);
                    int additionalProperty = Integer.parseInt(commandData[8]);
                    result.append(healthManager.addCell(name, clusterId, cellType, cellId, health, positionRow, positionCol, additionalProperty));
                    break;
                case "activateCluster":
                    result.append(healthManager.activateCluster(name));
                    break;
            }

            currentLine = reader.readLine();
        }
        System.out.println(result.toString());
    }
}
