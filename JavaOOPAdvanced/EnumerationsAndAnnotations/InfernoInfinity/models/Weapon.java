package JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.models;

import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.annotations.CustomAnnotation;

@CustomAnnotation(author = "Pesho", revision = 3, description =  "Used for Java OOP Advanced course - Enumerations and Annotations.", reviewers = {"Pesho", "Svetlio"})
public class Weapon implements Comparable<Weapon> {
    private static final int POINTS_TO_MIN_DAMAGE_FROM_STRENGTH = 2;
    private static final int POINTS_TO_MAX_DAMAGE_FROM_STRENGTH = 3;
    private static final int POINTS_TO_MIN_DAMAGE_FROM_AGILITY = 1;
    private static final int POINTS_TO_MAX_DAMAGE_FROM_AGILITY = 4;

    private String name;
    private WeaponType weaponType;
    private Gem[] gems;

    public Weapon(String name, WeaponType weaponType) {
        this.name = name;
        this.weaponType = weaponType;
        this.gems = new Gem[weaponType.getSocketsCount()];
    }

    public String getName() {
        return name;
    }

    public int getMaxDamage() {
        int maxDamage = this.weaponType.getMaxDamage();

        for (int i = 0; i < this.gems.length; i++) {
            Gem currentGem = this.gems[i];
            if (currentGem != null) {
                maxDamage += currentGem.getStrength()*POINTS_TO_MAX_DAMAGE_FROM_STRENGTH;
                maxDamage += currentGem.getAgility()*POINTS_TO_MAX_DAMAGE_FROM_AGILITY;
            }
        }

        return maxDamage;
    }

    public int getMinDamage() {
        int minDamage = this.weaponType.getMinDamage();

        for (int i = 0; i < this.gems.length; i++) {
            Gem currentGem = this.gems[i];
            if (currentGem != null) {
                minDamage += currentGem.getStrength()*POINTS_TO_MIN_DAMAGE_FROM_STRENGTH;
                minDamage += currentGem.getAgility()*POINTS_TO_MIN_DAMAGE_FROM_AGILITY;
            }
        }

        return minDamage;
    }

    public int getStrength() {
        int strength = 0;

        for (int i = 0; i < this.gems.length; i++) {
            Gem currentGem = this.gems[i];
            if (currentGem != null) {
                strength += currentGem.getStrength();
            }
        }

        return strength;
    }

    public int getAgility() {
        int agility = 0;

        for (int i = 0; i < this.gems.length; i++) {
            Gem currentGem = this.gems[i];
            if (currentGem != null) {
                agility += currentGem.getAgility();
            }
        }

        return agility;
    }

    public int getVitality() {
        int vitality = 0;

        for (int i = 0; i < this.gems.length; i++) {
            Gem currentGem = this.gems[i];
            if (currentGem != null) {
                vitality += currentGem.getVitality();
            }
        }

        return vitality;
    }

    public Gem[] getGems() {
        return gems;
    }

    public double getLevel() {
        return ((double)(this.getMaxDamage() + this.getMinDamage()) / 2)
                + this.getAgility() + this.getVitality() + this.getStrength();
    }

    public void addGem(int index, Gem gem) {
        if (index < this.gems.length && index > -1) {
            this.gems[index] = gem;
        }
    }

    public void removeGem(int index) {
        if (index < this.gems.length && index > -1) {
            this.gems[index] = null;
        }
    }

    public String printGreaterWeapon() {
        return this.toString() + String.format(" (Item Level: %.1f)", this.getLevel());
    }

    @Override
    public String toString() {
        return String.format(
            "%s: %d-%d Damage, +%d Strength, +%d Agility, +%d Vitality",
            this.getName(),
            this.getMinDamage(),
            this.getMaxDamage(),
            this.getStrength(),
            this.getAgility(),
            this.getVitality()
        );
    }

    @Override
    public int compareTo(Weapon weapon) {
        if (this.getLevel() >= weapon.getLevel()) {
            return 1;
        }

        return -1;
    }
}
