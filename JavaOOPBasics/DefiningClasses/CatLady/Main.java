package JavaOOPBasics.DefiningClasses.CatLady;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        ArrayList<Cat> cats = new ArrayList<>();

        while (!"End".equals(currentLine)) {
            String[] catData = currentLine.split(" ");
            String breed = catData[0];
            String name = catData[1];
            double  specificCharacteristic = Double.parseDouble(catData[2]);
            Cat cat = null;
            if (breed.equals("Siamese")) {
                cat = new Siamese(name, specificCharacteristic);
            } else if (breed.equals("Cymric")) {
                cat = new Cymric(name, specificCharacteristic);
            } else if (breed.equals("StreetExtraordinaire")) {
                cat = new StreetExtraordinaire(name, specificCharacteristic);
            }
            cats.add(cat);
            currentLine = reader.readLine();
        }

        String catName = reader.readLine();

        for (Cat cat: cats) {
            if (cat.getName().equals(catName)) {
                System.out.println(cat.toString());
                break;
            }
        }
    }
}
