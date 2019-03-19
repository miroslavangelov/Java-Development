package app.models.participants;

import app.models.Config;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

public class WarriorTest {
    private Warrior warrior;

    @Before
    public void setUp() {
        this.warrior = new Warrior();
        this.warrior.setStrength(Config.WARRIOR_BASE_STRENGTH);
    }

    @Test
    public void isAlive() {
        this.warrior.takeDamage(this.warrior.getHealth());

        Assert.assertFalse(this.warrior.isAlive());
    }

    @Test
    public void takeDamage() {
        double damage = 5;
        double warriorHealth = this.warrior.getHealth();

        this.warrior.takeDamage(damage);

        Assert.assertEquals(warriorHealth - damage, this.warrior.getHealth(), 0.1);
    }

    @Test
    public void levelUp() {
        double warriorHealth = this.warrior.getHealth();
        int warriorStrength = this.warrior.getStrength();
        int warriorDexterity = this.warrior.getDexterity();
        int warriorIntelligence = this.warrior.getIntelligence();

        this.warrior.levelUp();

        Assert.assertEquals(warriorHealth * 2 , this.warrior.getHealth(), 0.1);
        Assert.assertEquals(warriorStrength * 2, this.warrior.getStrength());
        Assert.assertEquals(warriorDexterity * 2, this.warrior.getDexterity());
        Assert.assertEquals(warriorIntelligence * 2, this.warrior.getIntelligence());
    }

//    @Test
//    public void giveReward() {
//        Warrior hero = new Warrior();
//        double heroGold = this.getGold(hero);
//        double receivedGold = this.getGold(this.warrior);
//
//        this.warrior.giveReward(hero);
//
//        Assert.assertEquals(0, this.getGold(this.warrior), 0.1);
//        Assert.assertEquals(heroGold + receivedGold, this.getGold(hero), 0.1);
//    }

    @Test
    public void receiveReward() {
        double reward = 200;
        double warriorGold = this.getGold(this.warrior);

        this.warrior.receiveReward(reward);

        Assert.assertEquals(warriorGold + reward,  this.getGold(this.warrior), 0.1);
    }

    private double getGold(Warrior warrior) {
        try {
            //Field gold = AbstractHero.class.getDeclaredField("gold");
            Field gold = Warrior.class.getDeclaredField("gold");
            gold.setAccessible(true);
            return (double) gold.get(warrior);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return 0d;
    }
}
