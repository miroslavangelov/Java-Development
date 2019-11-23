package DataStructures.QuadTreesKDTressIntervalTrees.Exercise.SweepAndPrune;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<GameObject> gameObjects = new ArrayList<>();
        Map<String, GameObject> gameObjectsByName = new LinkedHashMap<>();
        int moves = 0;
        String input = reader.readLine();

        while (!"end".equals(input)) {
            String[] inputData = input.split(" ");
            String command = inputData[0];

            switch (command) {
                case "add":
                    String name = inputData[1];
                    int x1 = Integer.parseInt(inputData[2]);
                    int y1 = Integer.parseInt(inputData[3]);
                    GameObject gameObject = new GameObject(name, x1, y1);

                    gameObjects.add(gameObject);
                    gameObjectsByName.put(name, gameObject);
                    break;
                case "start":
                    insertionSort(gameObjects);
                    break;
                case "tick":
                    moves++;
                    sweep(moves, gameObjects);
                    break;
                case "move":
                    name = inputData[1];
                    x1 = Integer.parseInt(inputData[2]);
                    y1 = Integer.parseInt(inputData[3]);

                    gameObjectsByName.get(name).setX1(x1);
                    gameObjectsByName.get(name).setY1(y1);
                    moves++;
                    sweep(moves, gameObjects);
                    break;
            }

            input = reader.readLine();
        }
    }

    private static void sweep(int moves, List<GameObject> gameObjects) {
        insertionSort(gameObjects);
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject current = gameObjects.get(i);

            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject collisionCandidates = gameObjects.get(j);

                if (collisionCandidates.getX1() > current.getX2()) {
                    break;
                }

                if (current.intersects(collisionCandidates)) {
                    System.out.println(String.format("(%d) %s collides with %s", moves, current.getName(), collisionCandidates.getName()));
                }
            }
        }
    }

    private static void insertionSort(List<GameObject> gameObjects) {
        for (int i = 1; i < gameObjects.size(); i++) {
            int j = i;

            while (j > 0 && gameObjects.get(j - 1).getX1() > gameObjects.get(j).getX1()){
                GameObject temp = gameObjects.get(j);
                gameObjects.set(j, gameObjects.get(j - 1));
                gameObjects.set(j - 1, temp);
                j--;
            }
        }
    }
}
