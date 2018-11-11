package JavaOOPAdvanced.UnitTesting.BubbleSort;

import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;
import java.util.Random;

public class BubbleSortTest {
    @Test
    public void testSortingWithZeroElements() throws OperationNotSupportedException {
        Integer[] testArray = new Integer[]{};
        Integer[] expectedArray = new Integer[]{};

        BubbleSort.sort(testArray);

        Assert.assertArrayEquals(expectedArray, testArray);
    }

    @Test
    public void testSortingWithOneElements() throws OperationNotSupportedException {
        Integer[] testArray = new Integer[]{-1};
        Integer[] expectedArray = new Integer[]{-1};

        BubbleSort.sort(testArray);

        Assert.assertArrayEquals(expectedArray, testArray);
    }

    @Test
    public void testSortingWithIntegers() throws OperationNotSupportedException {
        Integer[] testArray = new Integer[]{-1, -2, 3, 3, 15, 1, 10, 5};
        Integer[] expectedArray = new Integer[]{-2, -1, 1, 3, 3, 5, 10, 15};

        BubbleSort.sort(testArray);

        Assert.assertArrayEquals(expectedArray, testArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testSortingWithNull() throws OperationNotSupportedException {
        Integer[] testArray = new Integer[]{10, null};
        BubbleSort.sort(testArray);
    }

    @Test
    public void testSortingWithStrings() throws OperationNotSupportedException {
        String[] testArray = new String[]{"Element", "element", ""};
        String[] expectedArray = new String[]{"", "Element", "element"};

        BubbleSort.sort(testArray);

        Assert.assertArrayEquals(expectedArray, testArray);
    }

    @Test
    public void testSortingWithBigArray() throws OperationNotSupportedException {
        Integer[] elements = new Integer[1000];
        Random rnd = new Random();

        Integer[] expected = new Integer[1000];

        for (int i = 0; i < elements.length; i++) {
            int random = rnd.nextInt(Integer.MAX_VALUE);
            elements[i] = random;
            expected[i] = random;
        }

        BubbleSort.sort(elements);
        Arrays.sort(expected);

        Assert.assertArrayEquals(expected, elements);
    }
}
