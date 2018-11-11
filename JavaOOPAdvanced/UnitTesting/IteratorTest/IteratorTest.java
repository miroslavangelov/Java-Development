package JavaOOPAdvanced.UnitTesting.IteratorTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

public class IteratorTest {
    private static final String[] TEST_ARRAY = new String[]{"Element1", "Element2", "Element3"};

    private Iterator<String> iterator;

    @Before
    public void initializeIterator() {
        this.iterator = new Iterator<>(TEST_ARRAY);
    }

    @Test
    public void testHasNext() {
        boolean hasNext = this.iterator.hasNext();

        Assert.assertEquals("List does not have next element!", true, hasNext);
    }

    @Test
    public void testHasNextFalse() {
        this.iterator.move();
        this.iterator.move();
        this.iterator.move();

        Assert.assertFalse(this.iterator.hasNext());
    }

    @Test
    public void testMove() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput = "Element2" + System.lineSeparator();

        this.iterator.move();
        this.iterator.print();

        Assert.assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMoveIndex() throws NoSuchFieldException, IllegalAccessException {
        this.iterator.move();
        Field index = this.iterator.getClass().getDeclaredField("index");
        index.setAccessible(true);

        Assert.assertEquals("Index was not moved!", 1, index.get(this.iterator));
        index.setAccessible(false);
    }

    @Test
    public void testPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        this.iterator.print();
        String expectedOutput = "Element1" + System.lineSeparator();

        Assert.assertEquals("Wrong output!", expectedOutput, outContent.toString());
    }

    @Test
    public void testPrintException() throws IllegalArgumentException {
        this.iterator.move();
        this.iterator.move();
        this.iterator.move();

        this.iterator.print();
    }
}
