package JavaOOPAdvanced.UnitTesting.IntegrationTests;

import JavaOOPAdvanced.UnitTesting.IntegrationTests.models.Category;
import JavaOOPAdvanced.UnitTesting.IntegrationTests.models.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandManager {
    private Map<String, User> users;
    private Map<String, Category> categories;

    public CommandManager() {
        this.users = new LinkedHashMap<>();
        this.categories = new LinkedHashMap<>();
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public Map<String, Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        this.categories.putIfAbsent(category.getName(), category);
    }

    public void addUser(User user) {
        this.users.putIfAbsent(user.getName(), user);
    }

    public void removeCategory(String categoryName) {
        if (!this.categories.containsKey(categoryName)) {
            throw new IllegalArgumentException("Category not found.");
        }
        Category category = this.categories.get(categoryName);
        this.addUsersToChildCategory(category);
        this.categories.remove(categoryName);
    }

    public void addChildCategoryToParentCategory(String parentCategoryName, String childCategoryName) {
        if (!this.categories.containsKey(parentCategoryName)) {
            throw new IllegalArgumentException("Parent category not found.");
        }
        if (!this.categories.containsKey(childCategoryName)) {
            throw new IllegalArgumentException("Child category not found.");
        }

        Category parentCategory = this.categories.get(parentCategoryName);
        Category childCategory = this.categories.get(childCategoryName);

        parentCategory.addChildCategory(childCategory);
    }

    public void addUserToCategory(String userName, String categoryName) {
        if (!this.categories.containsKey(categoryName)) {
            throw new IllegalArgumentException("Category not found.");
        }
        if (!this.users.containsKey(userName)) {
            throw new IllegalArgumentException("User not found.");
        }

        User user = this.users.get(userName);
        Category category = this.categories.get(categoryName);

        user.addCategory(category);
        category.addUser(user);
    }

    private void addUsersToChildCategory(Category parentCategory) {
        Collection<Category> childCategories = parentCategory.getChildCategories().values();

        if (!childCategories.isEmpty()) {
            Collection<User> users = parentCategory.getUsers().values();

            for (User user: users) {
                user.removeCategory(parentCategory.getName());
                for (Category childCategory: childCategories) {
                    childCategory.addUser(user);
                }
            }

        }
    }
}
