package JavaOOPBasics.Polymorphism.WildFarm;

import java.text.DecimalFormat;

public class Cat extends Felime {
    private String breed;

    public Cat(String animalName, String animalType, double animalWeight, String livingRegion, String breed) {
        super(animalName, animalType, animalWeight, livingRegion);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#.##");
        return String.format("%s[%s, %s, %s, %s, %d]", this.getAnimalType(), this.getAnimalName(), this.getBreed(), format.format(this.getAnimalWeight()), this.getLivingRegion(), this.getFoodEaten());
    }

    @Override
    protected void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    protected void eat(Food food) {
        this.eatFood(food.getQuantity());
    }
}
