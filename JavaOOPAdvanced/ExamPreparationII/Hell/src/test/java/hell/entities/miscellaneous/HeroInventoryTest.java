package hell.entities.miscellaneous;

import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeroInventoryTest {
    private final static int MAX_VALUE = Integer.MIN_VALUE;

    private Inventory inventory;

    @Before
    public void setUp() {
        this.inventory = new HeroInventory();
    }

    @Test
    public void testGetTotalStrengthBonus() {
        this.addCommonItems();

        long actualTotalStrengthBonus = this.inventory.getTotalStrengthBonus();
        long expectedTotalStrengthBonus = 3L * MAX_VALUE;

        Assert.assertEquals(expectedTotalStrengthBonus, actualTotalStrengthBonus);
    }

    @Test
    public void testGetTotalAgilityBonus() {
        this.addCommonItems();

        long actualTotalAgilityBonus = this.inventory.getTotalAgilityBonus();
        long expectedTotalAgilityBonus = 3L * MAX_VALUE;

        Assert.assertEquals(expectedTotalAgilityBonus, actualTotalAgilityBonus);
    }

    @Test
    public void testGetTotalIntelligenceBonus() {
        this.addCommonItems();

        long actualTotalIntelligenceBonus = this.inventory.getTotalIntelligenceBonus();
        long expectedTotalIntelligenceBonus = 3L * MAX_VALUE;

        Assert.assertEquals(expectedTotalIntelligenceBonus, actualTotalIntelligenceBonus);
    }

    @Test
    public void testGetTotalHitPointsBonus() {
        this.addCommonItems();

        long actualTotalHitPointsBonus = this.inventory.getTotalHitPointsBonus();
        long expectedTotalHitPointsBonus = 3L * MAX_VALUE;

        Assert.assertEquals(expectedTotalHitPointsBonus, actualTotalHitPointsBonus);
    }

    @Test
    public void testGetTotalDamageBonus() {
        this.addCommonItems();

        long actualTotalDamageBonus = this.inventory.getTotalDamageBonus();
        long expectedTotalDamageBonus = 3L * MAX_VALUE;

        Assert.assertEquals(expectedTotalDamageBonus, actualTotalDamageBonus);
    }

    @Test
    public void testAddCommonItem() {
        this.addCommonItems();
        this.addRecipeItem();
        Item commonItem4 = this.createCommonItem();
        Mockito.when(commonItem4.getName()).thenReturn("Item4");
        this.inventory.addCommonItem(commonItem4);
        Map<String, Item> itemsMap = null;
        int expectedItemsCount = 2;

        try {
            Field commonItemsField = this.inventory.getClass().getDeclaredField("commonItems");
            commonItemsField.setAccessible(true);
            itemsMap = (Map<String, Item>) commonItemsField.get(this.inventory);
            commonItemsField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedItemsCount, itemsMap.size());
    }

    @Test
    public void testAddRecipeItem() {
        this.addRecipeItem();
        Map<String, Recipe> recipeItemsMap = null;
        int expectedItemsCount = 1;

        try {
            Field recipeItemsField = this.inventory.getClass().getDeclaredField("recipeItems");
            recipeItemsField.setAccessible(true);
            recipeItemsMap = (Map<String, Recipe>) recipeItemsField.get(this.inventory);
            recipeItemsField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedItemsCount, recipeItemsMap.size());
    }

    private Item createCommonItem() {
        Item commonItem = Mockito.mock(Item.class);

        Mockito.when(commonItem.getStrengthBonus()).thenReturn(MAX_VALUE);
        Mockito.when(commonItem.getAgilityBonus()).thenReturn(MAX_VALUE);
        Mockito.when(commonItem.getIntelligenceBonus()).thenReturn(MAX_VALUE);
        Mockito.when(commonItem.getHitPointsBonus()).thenReturn(MAX_VALUE);
        Mockito.when(commonItem.getDamageBonus()).thenReturn(MAX_VALUE);

        return commonItem;
    }

    private void addCommonItems() {
        Item commonItem1 = this.createCommonItem();
        Mockito.when(commonItem1.getName()).thenReturn("Item1");
        Item commonItem2 = this.createCommonItem();
        Mockito.when(commonItem2.getName()).thenReturn("Item2");
        Item commonItem3 = this.createCommonItem();
        Mockito.when(commonItem3.getName()).thenReturn("Item3");

        this.inventory.addCommonItem(commonItem1);
        this.inventory.addCommonItem(commonItem2);
        this.inventory.addCommonItem(commonItem3);
    }

    private void addRecipeItem() {
        Recipe recipe = Mockito.mock(Recipe.class);
        List<String> requiredItems = new ArrayList<>();
        requiredItems.add("Item1");
        requiredItems.add("Item2");
        requiredItems.add("Item4");
        Mockito.when(recipe.getRequiredItems()).thenReturn(requiredItems);
        this.inventory.addRecipeItem(recipe);
    }
}