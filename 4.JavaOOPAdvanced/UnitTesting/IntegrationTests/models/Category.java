package JavaOOPAdvanced.UnitTesting.IntegrationTests.models;

import java.util.HashMap;
import java.util.Map;

public class Category {
    private String name;
    private Map<String, User> users;
    private Map<String, Category> childCategories;

    public Category(String name) {
        this.name = name;
        this.users = new HashMap<>();
        this.childCategories = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Category> getChildCategories() {
        return childCategories;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void addChildCategory(Category category) {
        this.childCategories.putIfAbsent(category.getName(), category);
    }

    public void removeChildCategory(String categoryName) {
        this.childCategories.remove(categoryName);
    }

    public void addUser(User user) {
        this.users.putIfAbsent(user.getName(), user);
    }
}
