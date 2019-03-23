package JavaOOPBasics.InterfacesAndAbstraction.FoodShortage;

public class Pet implements IBirthdate {
    private String name;
    private String birthdate;

    public Pet(String name, String birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    @Override
    public boolean isBirthdateInYear(String year) {
        String[] birthdateData = this.getBirthdate().split("/");
        String currentYear = birthdateData[2];

        return currentYear.equals(year);
    }

    @Override
    public String getBirthdate() {
        return this.birthdate;
    }
}
