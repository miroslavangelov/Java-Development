package JavaOOPAdvanced.UnitTesting.IntegrationTests.tests;

import JavaOOPAdvanced.UnitTesting.IntegrationTests.CommandManager;
import JavaOOPAdvanced.UnitTesting.IntegrationTests.models.Category;
import JavaOOPAdvanced.UnitTesting.IntegrationTests.models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CommandManagerTest {
    private static final User[] USERS = new User[]{new User("Peter"), new User("John"), new User("George")};
    private static final Category[] CATEGORIES = new Category[]{new Category("Finance"), new Category("Business"), new Category("Investments")};

    private CommandManager commandManager;

    @Before
    public void init() {
        this.commandManager = new CommandManager();

        for (User user: USERS) {
            this.commandManager.addUser(user);
        }

        for (Category category: CATEGORIES) {
            this.commandManager.addCategory(category);
        }
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testAddUser() {
        User user = new User("Ben");

        this.commandManager.addUser(user);

        Assert.assertTrue(this.commandManager.getUsers().containsKey(user.getName()));
    }

    @Test
    public void testAddCategory() {
        Category category = new Category("Software Development");

        this.commandManager.addCategory(category);

        Assert.assertTrue(this.commandManager.getCategories().containsKey(category.getName()));
    }

    @Test
    public void testRemoveCategory() {
        String categoryName = "Finance";

        this.commandManager.removeCategory(categoryName);

        Assert.assertFalse(this.commandManager.getCategories().containsKey(categoryName));
    }

    @Test
    public void testAddChildCategoryToParentCategory() {
        String childCategoryName = "Finance";
        String parentCategoryName = "Business";

        this.commandManager.addChildCategoryToParentCategory(parentCategoryName, childCategoryName);

        Assert.assertTrue(this.commandManager.getCategories().get(parentCategoryName).getChildCategories().containsKey(childCategoryName));
    }

    @Test
    public void testAddChildCategoryToParentCategoryParentException() {
        String childCategoryName = "Finance";
        String parentCategoryName = "Invalid category";

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Parent category not found.");

        this.commandManager.addChildCategoryToParentCategory(parentCategoryName, childCategoryName);
    }

    @Test
    public void testAddChildCategoryToParentCategoryChildException() {
        String childCategoryName = "Invalid category";
        String parentCategoryName = "Finance";

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Child category not found.");

        this.commandManager.addChildCategoryToParentCategory(parentCategoryName, childCategoryName);
    }

    @Test
    public void testAddUserToCategory() {
        User user = this.commandManager.getUsers().get("Peter");
        Category category = this.commandManager.getCategories().get("Finance");

        this.commandManager.addUserToCategory(user.getName(), category.getName());

        Assert.assertTrue(this.commandManager.getCategories().get(category.getName()).getUsers().containsKey(user.getName()));
    }

    @Test
    public void testAddUserToCategoryUserException() {
        String categoryName = "Finance";
        String userName = "Invalid user";

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("User not found.");

        this.commandManager.addUserToCategory(userName, categoryName);
    }

    @Test
    public void testAddUserToCategoryCategoryException() {
        String categoryName = "Invalid category";
        String userName = "Peter";

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Category not found.");

        this.commandManager.addUserToCategory(userName, categoryName);
    }

    @Test
    public void testAddUsersToChildCategory() {
        Category parentCategory = this.commandManager.getCategories().get("Business");
        Category childCategory = this.commandManager.getCategories().get("Finance");
        User user = this.commandManager.getUsers().get("Peter");

        this.commandManager.addUserToCategory(user.getName(), parentCategory.getName());
        this.commandManager.addChildCategoryToParentCategory(parentCategory.getName(), childCategory.getName());
        this.commandManager.removeCategory(parentCategory.getName());

        Assert.assertTrue(this.commandManager.getCategories().get(childCategory.getName()).getUsers().containsKey(user.getName()));
    }

    @Test
    public void testMoveChildCategoryToAnotherParentCategory() {
        Category parentCategory = this.commandManager.getCategories().get("Business");
        Category newParentCategory = this.commandManager.getCategories().get("Investments");
        Category childCategory = this.commandManager.getCategories().get("Finance");
        User user = this.commandManager.getUsers().get("Peter");

        this.commandManager.addUserToCategory(user.getName(), childCategory.getName());
        this.commandManager.addChildCategoryToParentCategory(parentCategory.getName(), childCategory.getName());
        this.commandManager.getCategories().get(parentCategory.getName()).removeChildCategory(childCategory.getName());
        this.commandManager.addChildCategoryToParentCategory(newParentCategory.getName(), childCategory.getName());

        Assert.assertTrue(!this.commandManager.getCategories().get(parentCategory.getName()).getChildCategories().containsKey(childCategory.getName()));
        Assert.assertTrue(this.commandManager.getCategories().get(newParentCategory.getName()).getChildCategories().containsKey(childCategory.getName()));
        Assert.assertTrue(this.commandManager.getCategories().get(newParentCategory.getName()).getChildCategories().get(childCategory.getName()).getUsers().containsKey(user.getName()));
    }
}
