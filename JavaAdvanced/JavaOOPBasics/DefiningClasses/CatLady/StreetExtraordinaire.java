package JavaOOPBasics.DefiningClasses.CatLady;

public class StreetExtraordinaire extends Cat {
    private double decibelsOfMeows;

    public StreetExtraordinaire(String name, double decibelsOfMeows) {
        super(name, "StreetExtraordinaire");
        this.decibelsOfMeows = decibelsOfMeows;
    }

    public double getDecibelsOfMeows() {
        return this.decibelsOfMeows;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", this.getBreed(), this.getName(), this.getDecibelsOfMeows());
    }
}
