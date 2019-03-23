package JavaOOPAdvanced.ObjectCommunicationAndEvents.Problem7_1984.models;

public class Employee extends BaseEntity {
    private int income;

    public Employee(String id, String name, int income) {
        super(id, name);
        this.income = income;
    }

    public int getIncome() {
        return this.income;
    }

    public void setIncome(String income) {
        this.notifyAllObservers(
                String.format(
                        "--%s(ID:%s) changed income(int) from %d to %s",
                        this.getClass().getSimpleName(),
                        super.getId(),
                        this.getIncome(),
                        income
                ),
                "income"
        );
        this.income = Integer.parseInt(income);
    }
}
