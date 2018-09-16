package JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerformanceCar extends Car {
    private List<String> addOns;

    public PerformanceCar(String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        this.addOns = new ArrayList<>();
    }

    public List<String> getAddOns() {
        return Collections.unmodifiableList(addOns);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(super.toString());
        String addons;

        if (this.getAddOns().size() > 0) {
            addons = String.join(", ", this.getAddOns());
        } else {
            addons = "None";
        }
        result.append(String.format("Add-ons: %s%n", addons));

        return result.toString();
    }

    @Override
    protected void setHorsepower(int horsepower) {
        super.setHorsepower(horsepower + (horsepower*50)/100);
    }

    @Override
    protected void setSuspension(int suspension) {
        super.setSuspension(suspension - (suspension*25)/100);
    }
}
