package JavaOOPAdvanced.UnitTesting.ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class ExtendedDatabaseTest {
    private static final int CAPACITY = 16;
    private static final Person[] TEST_ARRAY = new Person[]{new Person(1, "Spas")};
    private static final Person NEW_PERSON = new Person(2, "Ivan");
    private static final Person INVALID_PERSON = new Person(-2, "Ivan");

    private ExtendedDatabase database;

    @Before
    public void initializeDatabase() throws OperationNotSupportedException {
        this.database = new ExtendedDatabase(TEST_ARRAY);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testDatabaseAdd() throws OperationNotSupportedException {
        this.database.add(NEW_PERSON);
        boolean actual = Arrays.asList(this.database.fetch()).contains(NEW_PERSON);

        Assert.assertEquals("Person not added!", true, actual);
    }

    @Test
    public void testDatabaseAddWithFullCollection() throws OperationNotSupportedException {
        expectedException.expect(OperationNotSupportedException.class);
        expectedException.expectMessage("Collection is full!");

        for (int i = 2; i <= 17; i++) {
            Person person = new Person(i, "User" + i);
            this.database.add(person);
        }
    }

    @Test
    public void testDatabaseAddDuplicatePerson() throws OperationNotSupportedException {
        expectedException.expect(OperationNotSupportedException.class);
        expectedException.expectMessage("Person should be unique!");

        this.database.add(NEW_PERSON);
        this.database.add(NEW_PERSON);
    }

    @Test
    public void testDatabaseAddPersonWithNegativeId() throws OperationNotSupportedException {
        expectedException.expect(OperationNotSupportedException.class);
        expectedException.expectMessage("Id should have a positive value!");

        this.database.add(INVALID_PERSON);
    }

    @Test
    public void testDatabaseRemove() throws OperationNotSupportedException {
        this.database.remove();
        boolean isPresent = Arrays.asList(this.database.fetch()).contains(NEW_PERSON);
        Assert.assertEquals("Element not removed!", isPresent, false);
    }

    @Test
    public void testDatabaseRemoveFromEmptyCollection() throws OperationNotSupportedException {
        expectedException.expect(OperationNotSupportedException.class);
        expectedException.expectMessage("Collection is empty!");

        for (int i = 0; i < CAPACITY; i++) {
            this.database.remove();
        }
    }

    @Test
    public void testFindById() throws OperationNotSupportedException {
        this.database.add(NEW_PERSON);
        Person user = this.database.findById(NEW_PERSON.getId());
        Assert.assertEquals("User not found!", NEW_PERSON, user);
    }

    @Test
    public void testFindByIdWithNotAddedUser() throws OperationNotSupportedException {
        expectedException.expect(OperationNotSupportedException.class);
        expectedException.expectMessage("User not found!");

        this.database.findById(NEW_PERSON.getId());
    }

    @Test
    public void testFindByUsername() throws OperationNotSupportedException {
        this.database.add(NEW_PERSON);
        Person user = this.database.findByUsername(NEW_PERSON.getUsername());
        Assert.assertEquals("User not found!", NEW_PERSON, user);
    }

    @Test
    public void testFindByUsernameWithNotAddedUser() throws OperationNotSupportedException {
        expectedException.expect(OperationNotSupportedException.class);
        expectedException.expectMessage("User not found!");

        this.database.findByUsername(NEW_PERSON.getUsername());
    }

    @Test
    public void testFindByUsernameWithNullUsername() throws OperationNotSupportedException {
        expectedException.expect(OperationNotSupportedException.class);
        expectedException.expectMessage("Username should not be null!");

        this.database.findByUsername(null);
    }
}
