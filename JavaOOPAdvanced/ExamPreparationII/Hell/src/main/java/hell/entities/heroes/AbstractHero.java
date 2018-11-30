package hell.entities.heroes;

import hell.entities.miscellaneous.ItemCollection;
import hell.interfaces.Hero;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public abstract class AbstractHero implements Hero {
    private String name;
    private int strength;
    private int agility;
    private int intelligence;
    private int hitPoints;
    private int damage;
    private Inventory inventory;

    protected AbstractHero(
            String name,
            int strength,
            int agility,
            int intelligence,
            int hitPoints,
            int damage,
            Inventory inventory
    ) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.inventory = inventory;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength + this.inventory.getTotalStrengthBonus();
    }

    @Override
    public long getAgility() {
        return this.agility + this.inventory.getTotalAgilityBonus();
    }

    @Override
    public long getIntelligence() {
        return this.intelligence + this.inventory.getTotalIntelligenceBonus();
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints + this.inventory.getTotalHitPointsBonus();
    }

    @Override
    public long getDamage() {
        return this.damage + this.inventory.getTotalDamageBonus();
    }

    @Override
    public Collection<Item> getItems() {
        Collection<Item> items = null;
        Field[] inventoryFields = this.inventory.getClass().getDeclaredFields();

        for (Field inventoryField : inventoryFields) {
            if (inventoryField.isAnnotationPresent(ItemCollection.class)) {
                Map<String, Item> itemMap = null;
                inventoryField.setAccessible(true);
                try {
                    itemMap = (Map<String, Item>) inventoryField.get(this.inventory);
                    inventoryField.setAccessible(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                items = itemMap.values();
            }
        }

        return items;
    }

    @Override
    public void addItem(Item item) {
        this.inventory.addCommonItem(item);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.inventory.addRecipeItem(recipe);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("Hero: %s, Class: %s", this.getName(), this.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append(String.format("HitPoints: %d, Damage: %d", this.getHitPoints(), this.getDamage()))
                .append(System.lineSeparator())
                .append(String.format("Strength: %d", this.getStrength()))
                .append(System.lineSeparator())
                .append(String.format("Agility: %d", this.getAgility()))
                .append(System.lineSeparator())
                .append(String.format("Intelligence: %d", this.getIntelligence()))
                .append(System.lineSeparator());

        Collection<Item> items = this.getItems();

        if (items.size() > 0) {
            result.append("Items:");
            for (Item item : items) {
                result.append(System.lineSeparator()).append(item.toString());
            }
        } else {
            result.append("Items: None");
        }

        return result.toString();
    }
}
