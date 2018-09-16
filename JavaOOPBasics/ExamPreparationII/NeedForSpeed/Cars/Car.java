package JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars;

public abstract class Car {
    private String brand;
    private String model;
    private int yearOfProduction;
    private int horsepower;
    private int acceleration;
    private int suspension;
    private int durability;
    private int performancePoints;
    private int moneyWon;

    protected Car(String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        this.setBrand(brand);
        this.setModel(model);
        this.setYearOfProduction(yearOfProduction);
        this.setDurability(durability);
        this.setAcceleration(acceleration);
        this.setHorsepower(horsepower);
        this.setSuspension(suspension);
        this.setPerformancePoints(0);
        this.setMoneyWon(0);
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public int getSuspension() {
        return suspension;
    }

    public int getDurability() {
        return durability;
    }

    public int getPerformancePoints() {
        return performancePoints;
    }

    public int getMoneyWon() {
        return moneyWon;
    }

    public void setSuspension(int suspension) {
        this.suspension = suspension;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public void setPerformancePoints(int performancePoints) {
        this.performancePoints = performancePoints;
    }

    public void setMoneyWon(int moneyWon) {
        this.moneyWon = moneyWon;
    }

    public void tune(int tuneIndex, String addon) {
        this.horsepower += tuneIndex;
        this.suspension += tuneIndex/2;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s %s %d%n", this.getBrand(), this.getModel(), this.getYearOfProduction()))
                .append(String.format("%d HP, 100 m/h in %d s%n", this.getHorsepower(), this.getAcceleration()))
                .append(String.format("%d Suspension force, %d Durability%n", this.getSuspension(), this.getDurability()));

        return result.toString();
    }
}
