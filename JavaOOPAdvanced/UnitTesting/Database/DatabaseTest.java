package JavaOOPAdvanced.UnitTesting.Database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private static final int CAPACITY = 16;
    private static final int WRONG_CAPACITY = 17;
    private static final Integer[] TEST_ARRAY = new Integer[]{1, 2, 3};
    private static final int NEW_ELEMENT = 4;

    private Database database;

    @Before
    public void initializeDatabase() throws OperationNotSupportedException {
        this.database = new Database(TEST_ARRAY);
    }

    @Test(expected = InvocationTargetException.class)
    public void testDatabaseWrongCapacity() throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method setStoredIntegers = this.database.getClass().getDeclaredMethod("setStoredIntegers", Integer[].class);
        Field storedIntegersField = this.database.getClass().getDeclaredField("storedIntegers");
        setStoredIntegers.setAccessible(true);
        storedIntegersField.setAccessible(true);

        storedIntegersField.set(this.database, new Integer[WRONG_CAPACITY]);
        setStoredIntegers.invoke(this.database, new Object[] {TEST_ARRAY});

        setStoredIntegers.setAccessible(false);
        setStoredIntegers.setAccessible(false);
    }

    @Test
    public void testDatabaseCapacity() throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method setStoredIntegers = this.database.getClass().getDeclaredMethod("setStoredIntegers", Integer[].class);
        Field storedIntegersField = this.database.getClass().getDeclaredField("storedIntegers");
        setStoredIntegers.setAccessible(true);
        storedIntegersField.setAccessible(true);

        storedIntegersField.set(this.database, new Integer[CAPACITY]);
        setStoredIntegers.invoke(this.database, new Object[] {TEST_ARRAY});

        setStoredIntegers.setAccessible(false);
        setStoredIntegers.setAccessible(false);

        Assert.assertEquals("Wrong capacity!", CAPACITY, this.database.fetch().length);
    }

    @Test
    public void testDatabaseAdd() throws OperationNotSupportedException {
        this.database.add(NEW_ELEMENT);
        boolean actual = Arrays.asList(this.database.fetch()).contains(NEW_ELEMENT);

        Assert.assertEquals("Element not added!", true, actual);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testDatabaseAddWithFullCollection() throws OperationNotSupportedException {
        for (int i = 0; i < CAPACITY; i++) {
            this.database.add(NEW_ELEMENT);
        }
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testDatabaseAddNullElement() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test
    public void testDatabaseRemove() throws OperationNotSupportedException {
        this.database.remove();
        boolean isPresent = Arrays.asList(this.database.fetch()).contains(NEW_ELEMENT);
        Assert.assertEquals("Element not removed!", isPresent, false);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testDatabaseRemoveFromEmptyCollection() throws OperationNotSupportedException {
        for (int i = 0; i < CAPACITY; i++) {
            this.database.remove();
        }
    }
}
