package JavaOOPBasics.Polymorphism.WildFarm;

public class Zebra extends Mammal {
    public Zebra(String animalName, String animalType, double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    protected void makeSound() {
        System.out.println("Zs");
    }

    @Override
    protected void eat(Food food) {
        if (food instanceof Vegetable) {
            this.eatFood(food.getQuantity());
        } else {
            System.out.println(String.format("%ss are not eating that type of food!", this.getAnimalType()));
        }
    }
}
