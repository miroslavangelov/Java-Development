package JavaOOPBasics.InterfacesAndAbstraction.MooD3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] gameObjectData = reader.readLine().split(" \\| ");
        String userName = gameObjectData[0];
        String gameObjectType = gameObjectData[1];
        int level = Integer.parseInt(gameObjectData[3]);

        switch(gameObjectType) {
            case "Demon":
                double energy = Double.parseDouble(gameObjectData[2]);
                GameObject demon = new Demon(userName, level, energy);
                System.out.println(demon.toString());
                break;
            case "Archangel":
                int mana = Integer.parseInt(gameObjectData[2]);
                GameObject archangel = new Archangel(userName, level, mana);
                System.out.println(archangel.toString());
                break;
        }
    }
}
