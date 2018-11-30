package hell.entities.heroes;

import hell.interfaces.Inventory;

public class Barbarian extends AbstractHero {
    private final static int BARBARIAN_STRENGTH = 90;
    private final static int BARBARIAN_AGILITY = 25;
    private final static int BARBARIAN_INTELLIGENCE = 10;
    private final static int BARBARIAN_HIT_POINTS = 350;
    private final static int BARBARIAN_DAMAGE = 150;

    public Barbarian(String name, Inventory inventory) {
        super(
                name,
                BARBARIAN_STRENGTH,
                BARBARIAN_AGILITY,
                BARBARIAN_INTELLIGENCE,
                BARBARIAN_HIT_POINTS,
                BARBARIAN_DAMAGE,
                inventory
        );
    }
}
