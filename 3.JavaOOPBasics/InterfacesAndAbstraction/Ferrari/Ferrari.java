package JavaOOPBasics.InterfacesAndAbstraction.Ferrari;

public class Ferrari implements Car{
    private static final String model = "488-Spider";
    private String driver;

    public Ferrari(String driver) {
        this.driver = driver;
    }

    public String getDriver() {
        return driver;
    }

    @Override
    public String brakes() {
        return "Brakes!";
    }

    @Override
    public String gas() {
        return "Zadu6avam sA!";
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s/%s", model, this.brakes(), this.gas(), this.getDriver());
    }
}
