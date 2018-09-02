package JavaOOPBasics.Polymorphism.Vehicles;

public class Car extends Vehicle {
    public Car(double fuel, double consumption) {
        super(fuel, consumption);
    }

    @Override
    protected void setConsumption(double consumption) {
        super.setConsumption(consumption + 0.9);
    }
}
