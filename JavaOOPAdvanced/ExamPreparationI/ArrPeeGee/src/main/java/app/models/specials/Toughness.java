package app.models.specials;

import app.models.Config;
import app.contracts.Hero;

public class Toughness extends AbstractSpecial {
    @Override
    public void trigger(Hero hero) {
        if (!super.isActive()) {
            if (hero.getHealth() <= (hero.getStrength() * Config.HERO_HEALTH_MULTIPLIER) / 2) {
                this.setActive(true);
                hero.setStrength(hero.getStrength() + hero.getIntelligence());
            }
        }

        if (super.isActive()) {
            if (hero.getHealth() > (hero.getStrength() * Config.HERO_HEALTH_MULTIPLIER) / 2) {
                this.setActive(false);
                hero.setStrength(hero.getStrength() - hero.getIntelligence());
            }
        }
    }

    @Override
    public void reset(Hero hero) {
        if (super.isActive()) {
            hero.setStrength(hero.getStrength() - hero.getIntelligence());
            this.setActive(false);
        }
    }
}
