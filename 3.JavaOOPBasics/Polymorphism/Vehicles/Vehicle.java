package JavaOOPBasics.Polymorphism.Vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle {
    private double fuel;
    private double consumption;

    public Vehicle(double fuel, double consumption) {
        this.setFuel(fuel);
        this.setConsumption(consumption);
    }

    public String driveDistance(double distance) {
        double consumedFuel = distance*this.consumption;

        if (consumedFuel > this.fuel) {
            return String.format("%s needs refueling", this.getClass().getSimpleName()) + "\n";
        } else {
            this.fuel -= consumedFuel;
            DecimalFormat format = new DecimalFormat("#.##");
            return String.format("%s travelled %s km", this.getClass().getSimpleName(), format.format(distance)) + "\n";
        }
    }

    public void refuel(double additionalFuel) {
        this.fuel += additionalFuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    protected void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getFuel() {
        return fuel;
    }
}
