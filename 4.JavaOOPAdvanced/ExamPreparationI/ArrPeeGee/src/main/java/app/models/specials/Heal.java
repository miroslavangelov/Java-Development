package app.models.specials;

import app.models.Config;
import app.contracts.Hero;

public class Heal extends AbstractSpecial {
    private int counter;

    @Override
    public void trigger(Hero hero) {
        if (!super.isActive()) {
            if (hero.getHealth() <= (hero.getStrength() * Config.HERO_HEALTH_MULTIPLIER) / 2) {
                this.setActive(true);
                this.counter = 1;
            }
        }

        if (super.isActive()) {
            hero.setHealth(hero.getHealth() + hero.getIntelligence());
            counter--;
            if (counter <= 0) {
                super.setActive(false);
            }
        }
    }

    @Override
    public void reset(Hero hero) {
        if (this.isActive()) {
            this.setActive(false);
            this.counter = 1;
        }
    }
}
