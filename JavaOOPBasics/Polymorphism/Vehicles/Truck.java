package JavaOOPBasics.Polymorphism.Vehicles;

public class Truck extends Vehicle {
    public Truck(double fuel, double consumption) {
        super(fuel, consumption);
    }

    @Override
    public void refuel(double fuelQuantity) {
        super.refuel(fuelQuantity*0.95);
    }

    @Override
    protected void setConsumption(double consumption) {
        super.setConsumption(consumption + 1.6);
    }
}
