package JavaOOPBasics.Polymorphism.WildFarm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        LinkedList<Mammal> animals = new LinkedList<>();

        while (!"End".equals(currentLine)) {
            String[] animalData = currentLine.split(" ");
            String animalType = animalData[0];
            String animalName = animalData[1];
            Double animalWeight = Double.parseDouble(animalData[2]);
            String animalLivingRegion = animalData[3];

            String[] foodData = reader.readLine().split(" ");
            String foodType = foodData[0];
            int foodQuantity = Integer.parseInt(foodData[1]);
            Food food = null;
            switch (foodType) {
                case "Vegetable": food = new Vegetable(foodQuantity);break;
                case "Meat": food = new Meat(foodQuantity);break;
            }

            if (animalData.length == 5) {
                String catBreed = animalData[4];
                Mammal cat = new Cat(animalName, animalType, animalWeight, animalLivingRegion, catBreed);
                cat.makeSound();
                cat.eat(food);
                animals.add(cat);
            } else {
                switch (animalType) {
                    case "Tiger":
                        Mammal tiger = new Tiger(animalName, animalType, animalWeight, animalLivingRegion);
                        tiger.makeSound();
                        tiger.eat(food);
                        animals.add(tiger);
                        break;
                    case "Zebra":
                        Mammal zebra = new Zebra(animalName, animalType, animalWeight, animalLivingRegion);
                        zebra.makeSound();
                        zebra.eat(food);
                        animals.add(zebra);
                        break;
                    case "Mouse":
                        Mammal mouse = new Mouse(animalName, animalType, animalWeight, animalLivingRegion);
                        mouse.makeSound();
                        mouse.eat(food);
                        animals.add(mouse);
                        break;
                }
            }

            currentLine = reader.readLine();
        }

        for (Animal animal: animals) {
            System.out.println(animal.toString());
        }
    }
}
