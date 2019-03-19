package JavaOOPBasics.InterfacesAndAbstraction.FoodShortage;

public class Human extends Citizen implements IBirthdate, Buyer {
    private static final int FOOD_FOR_CITIZEN = 10;
    private String name;
    private int age;
    private String birthdate;
    private int food;

    public Human(String name, int age, String id, String birthdate) {
        super(id);
        this.name = name;
        this.age = age;
        this.birthdate = birthdate;
        this.food = 0;
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

    @Override
    public int getFood() {
        return this.food;
    }

    @Override
    public void buyFood() {
        this.food += FOOD_FOR_CITIZEN;
    }
}
