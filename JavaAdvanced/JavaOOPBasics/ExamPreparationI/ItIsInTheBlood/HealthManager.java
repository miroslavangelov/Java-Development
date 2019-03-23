package JavaOOPBasics.ExamPreparationI.ItIsInTheBlood;

import JavaOOPBasics.ExamPreparationI.ItIsInTheBlood.cells.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class HealthManager {
    private Map<String, Organism> organisms;

    public HealthManager() {
        this.organisms = new LinkedHashMap<>();
    }

    public String checkCondition(String organismName) {
        if (this.organisms.containsKey(organismName)) {
            return this.organisms.get(organismName).toString();
        }

        return "";
    }

    public String createOrganism(String name) {
        if (this.organisms.containsKey(name)) {
            return String.format("Organism %s already exists%n", name);
        }
        Organism organism = new Organism(name);
        this.organisms.put(name, organism);

        return String.format("Created organism %s%n", name);
    }

    public String addCluster(String organismName, String id, int rows, int cols) {
        if (!this.organisms.containsKey(organismName) || rows < 0 || cols < 0) {
            return "";
        }

        Organism organism = organisms.get(organismName);
        if (organism.getClusters().containsKey(id)) {
            return "";
        }

        Cluster cluster = new Cluster(id, rows, cols);
        organism.addCluster(cluster);
        return String.format("Organism %s: Created cluster %s%n", organismName, id);
    }

    public String addCell(String organismName, String clusterId, String cellType, String cellId, int health, int positionRow, int positionCol, int additionalProperty) {
        Organism organism = this.organisms.get(organismName);
        if (organism == null || health < 0 || positionRow < 0 || positionCol < 0 || additionalProperty < 0) {
            return "";
        }

        Cluster cluster = organism.getClusters().get(clusterId);
        if (cluster == null) {
            return "";
        }

        if (positionCol > cluster.getCols() - 1 || positionRow > cluster.getRows() - 1) {
            return "";
        }

        switch (cellType) {
            case "RedBloodCell":
                Cell redBloodCell = new RedBloodCell(cellId, health, positionRow, positionCol, additionalProperty);
                cluster.addCell(redBloodCell);
                break;
            case "WhiteBloodCell":
                Cell whiteBloodCell = new WhiteBloodCell(cellId, health, positionRow, positionCol, additionalProperty);
                cluster.addCell(whiteBloodCell);
                break;
            case "Bacteria":
                Cell bacteria = new Bacteria(cellId, health, positionRow, positionCol, additionalProperty);
                cluster.addCell(bacteria);
                break;
            case "Virus":
                Cell virus = new Virus(cellId, health, positionRow, positionCol, additionalProperty);
                cluster.addCell(virus);
                break;
            case "Fungi":
                Cell fungi = new Fungi(cellId, health, positionRow, positionCol, additionalProperty);
                cluster.addCell(fungi);
                break;
            default: return "";
        }

        return String.format("Organism %s: Created cell %s in cluster %s%n", organismName, cellId, clusterId);
    }

    public String activateCluster(String organismName) {
        Organism organism = this.organisms.get(organismName);
        if (organism == null) {
            return "";
        }
        if (organism.getClusters().size() <= 0) {
            return "";
        }

        Map.Entry<String, Cluster> activatedCluster = organism.getClusters().entrySet().iterator().next();
        if (!activatedCluster.getValue().getCells().isEmpty()) {
            Cell firstCell = activatedCluster.getValue().getCells().get(0);

            for (int i = 1; i < activatedCluster.getValue().getCells().size(); i++) {
                Cell currentCell = activatedCluster.getValue().getCells().get(i);
                firstCell = firstCell.attack(currentCell);
            }
            activatedCluster.getValue().clearCells();
            activatedCluster.getValue().addCell(firstCell);
        }

        organism.removeCluster(activatedCluster.getKey());
        organism.addCluster(activatedCluster.getKey(), activatedCluster.getValue());

        return String.format("Organism %s: Activated cluster %s. Cells left: %d%n", organismName, activatedCluster.getValue().getId(), activatedCluster.getValue().getCells().size());
    }
}
