package JavaOOPAdvanced.UnitTesting.Hack;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class HackTest {
    @Test
    public void testMathAbs() {
        int actual = Math.abs(-3);
        int expected = 3;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLineSeparator() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        System.out.print(lineSeparator);

        Assert.assertTrue(outContent.toString().contains(System.getProperty("line.separator")));
    }

    @Test
    public void testMathFloor() {
        double actual = Math.floor(-3.7);
        double expected = -4.0;

        Assert.assertEquals(expected, actual, 0.1);
    }
}
