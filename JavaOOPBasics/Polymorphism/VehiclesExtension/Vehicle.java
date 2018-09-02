package JavaOOPBasics.Polymorphism.VehiclesExtension;

import java.text.DecimalFormat;

public abstract class Vehicle {
    private double fuel;
    private double consumption;
    private double tankCapacity;

    public Vehicle(double fuel, double consumption, double tankCapacity) {
        this.setFuel(fuel);
        this.setConsumption(consumption);
        this.setTankCapacity(tankCapacity);
    }

    public void driveDistance(double distance, double additionalSummerConsumption) {
        double consumedFuel = distance*(this.consumption + additionalSummerConsumption);

        if (consumedFuel > this.fuel) {
            System.out.println(String.format("%s needs refueling", this.getClass().getSimpleName()));
        } else {
            this.fuel -= consumedFuel;
            DecimalFormat format = new DecimalFormat("#.##");
            System.out.println(String.format("%s travelled %s km", this.getClass().getSimpleName(), format.format(distance)));
        }
    }

    public void refuel(double additionalFuel) {
        if (additionalFuel <= 0) {
            System.out.println("Fuel must be a positive number");
        } else if (additionalFuel + this.fuel > this.tankCapacity) {
            System.out.println("Cannot fit fuel in tank");
        } else {
            this.fuel += additionalFuel;
        }
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

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }
}
