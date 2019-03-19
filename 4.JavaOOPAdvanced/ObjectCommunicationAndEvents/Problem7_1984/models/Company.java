package JavaOOPAdvanced.ObjectCommunicationAndEvents.Problem7_1984.models;

public class Company extends BaseEntity {
    private int turnover;
    private int revenue;

    public Company(String id, String name, int turnover, int revenue) {
        super(id, name);
        this.turnover = turnover;
        this.revenue = revenue;
    }

    public int getTurnover() {
        return this.turnover;
    }

    public void setTurnover(String turnover) {
        this.notifyAllObservers(
                String.format(
                        "--%s(ID:%s) changed turnover(int) from %d to %s",
                        this.getClass().getSimpleName(),
                        super.getId(),
                        this.getTurnover(),
                        turnover
                ),
                "turnover"
        );
        this.turnover = Integer.parseInt(turnover);
    }

    public int getRevenue() {
        return this.revenue;
    }

    public void setRevenue(String revenue) {
        this.notifyAllObservers(
                String.format(
                        "--%s(ID:%s) changed revenue(int) from %d to %s",
                        this.getClass().getSimpleName(),
                        super.getId(),
                        this.getRevenue(),
                        revenue
                ),
                "revenue"
        );
        this.revenue = Integer.parseInt(revenue);
    }
}
