package JavaOOPBasics.Polymorphism.VehiclesExtension;

public class Car extends Vehicle {
    public Car(double fuel, double consumption, double tankCapacity) {
        super(fuel, consumption, tankCapacity);
    }

    @Override
    protected void setConsumption(double consumption) {
        super.setConsumption(consumption + 0.9);
    }
}
