package JavaOOPBasics.ExamPreparationI.ItIsInTheBlood;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Organism {
    private String name;
    private Map<String, Cluster> clusters;

    public Organism(String name) {
        this.name = name;
        this.clusters = new LinkedHashMap<>();
    }

    public void addCluster(Cluster cluster) {
        this.clusters.put(cluster.getId(), cluster);
    }

    public Map<String, Cluster> getClusters() {
        return Collections.unmodifiableMap(this.clusters);
    }

    public String getName() {
        return this.name;
    }

    public void removeCluster(String clusterId) {
        this.clusters.remove(clusterId);
    }

    public void addCluster(String clusterId, Cluster cluster) {
        this.clusters.put(clusterId, cluster);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("Organism - %s%n", this.getName()))
                .append(String.format("--Clusters: %d%n", this.getClusters().size()))
                .append(String.format("--Cells: %d%n", this.getCellsCount()));

        for (Cluster cluster: this.getClusters().values()) {
            result.append(cluster.toString());
            for (Cell cell: cluster.getCells()) {
                result.append(cell.toString());
            }
        }

        return result.toString();
    }

    private int getCellsCount() {
        int totalCellsCount = 0;
        for (Cluster cluster: this.getClusters().values()) {
            totalCellsCount += cluster.getCells().size();
        }

        return totalCellsCount;
    }
}
