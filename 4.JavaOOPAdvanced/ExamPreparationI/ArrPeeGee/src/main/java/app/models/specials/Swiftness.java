package app.models.specials;

import app.models.Config;
import app.contracts.Hero;

public class Swiftness extends AbstractSpecial {
    @Override
    public void trigger(Hero hero) {
        if (!super.isActive()) {
            if (hero.getHealth() >= (hero.getStrength() * Config.HERO_HEALTH_MULTIPLIER) / 2) {
                super.setActive(true);
                hero.setDexterity(hero.getDexterity() + hero.getIntelligence());
            }
        }

        if (super.isActive()) {
            if (hero.getHealth() < (hero.getStrength() * Config.HERO_HEALTH_MULTIPLIER) / 2) {
                this.setActive(false);
                hero.setDexterity(hero.getDexterity() - hero.getIntelligence());
            }
        }
    }

    @Override
    public void reset(Hero hero) {
        if (super.isActive()) {
            hero.setDexterity(hero.getDexterity() - hero.getIntelligence());
            this.setActive(false);
        }
    }
}
