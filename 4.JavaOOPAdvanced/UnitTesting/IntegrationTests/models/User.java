package JavaOOPAdvanced.UnitTesting.IntegrationTests.models;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private Map<String, Category> categories;

    public User(String name) {
        this.name = name;
        this.categories = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        this.categories.putIfAbsent(category.getName(), category);
    }

    public void removeCategory(String categoryName) {
        this.categories.remove(categoryName);
    }
}
