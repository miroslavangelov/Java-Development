package hell.entities.heroes;

import hell.interfaces.Inventory;

public class Wizard extends AbstractHero {
    private final static int WIZARD_STRENGTH = 25;
    private final static int WIZARD_AGILITY = 25;
    private final static int WIZARD_INTELLIGENCE = 100;
    private final static int WIZARD_HIT_POINTS = 100;
    private final static int WIZARD_DAMAGE = 250;

    public Wizard(String name, Inventory inventory) {
        super(
                name,
                WIZARD_STRENGTH,
                WIZARD_AGILITY,
                WIZARD_INTELLIGENCE,
                WIZARD_HIT_POINTS,
                WIZARD_DAMAGE,
                inventory
        );
    }
}
