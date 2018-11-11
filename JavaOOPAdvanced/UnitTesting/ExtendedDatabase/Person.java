package JavaOOPAdvanced.UnitTesting.ExtendedDatabase;

public class Person {
    private long id;
    private String username;

    public Person(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
