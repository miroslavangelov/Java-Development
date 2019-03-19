package JavaOOPAdvanced.Reflection.BarracksWars.models.units;

public class Horseman extends AbstractUnit {
    private static final int HORSEMAN_HEALTH = 50;
    private static final int HORSEMAN_DAMAGE = 13;

    public Horseman() {
        super(HORSEMAN_HEALTH, HORSEMAN_DAMAGE);
    }
}

