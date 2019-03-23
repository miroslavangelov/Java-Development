package hell.entities.heroes;

import hell.interfaces.Inventory;

public class Assassin extends AbstractHero {
    private final static int ASSASSIN_STRENGTH = 25;
    private final static int ASSASSIN_AGILITY = 100;
    private final static int ASSASSIN_INTELLIGENCE = 15;
    private final static int ASSASSIN_HIT_POINTS = 150;
    private final static int ASSASSIN_DAMAGE = 300;

    public Assassin(String name, Inventory inventory) {
        super(
                name,
                ASSASSIN_STRENGTH,
                ASSASSIN_AGILITY,
                ASSASSIN_INTELLIGENCE,
                ASSASSIN_HIT_POINTS,
                ASSASSIN_DAMAGE,
                inventory
        );
    }
}
