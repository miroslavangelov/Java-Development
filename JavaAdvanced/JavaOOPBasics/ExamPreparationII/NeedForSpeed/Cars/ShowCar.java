package JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars;

public class ShowCar extends Car {
    private int stars;

    public ShowCar(String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        this.setStars(0);
    }

    public int getStars() {
        return this.stars;
    }

    private void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public void tune(int tuneIndex, String addon) {
        super.tune(tuneIndex, addon);
        this.setStars(this.stars + tuneIndex);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());

        result.append(String.format("%d *%n", this.getStars()));

        return result.toString();
    }
}
