package JavaOOPBasics.InterfacesAndAbstraction.BirthdayCelebrations;

public class Human extends Citizen implements IBirthdate {
    private String name;
    private int age;
    private String birthdate;

    public Human(String name, int age, String id, String birthdate) {
        super(id);
        this.name = name;
        this.age = age;
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
