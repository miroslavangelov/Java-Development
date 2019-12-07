package DataStructures.ExamPreparations.Invaders;

public class InvaderImpl implements Invader {
    private int damage;
    private int distance;

    public InvaderImpl(int damage, int distance) {
        this.damage = damage;
        this.distance = distance;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int compareTo(Invader o) {
        int compare = Integer.compare(this.distance, o.getDistance());

        if (compare == 0) {
            compare = Integer.compare(this.damage, o.getDamage());
        }

        return compare;
    }
}
