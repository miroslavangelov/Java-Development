package JavaOOPAdvanced.UnitTesting.Twitter.tests;

import JavaOOPAdvanced.UnitTesting.Twitter.interfaces.Tweet;
import JavaOOPAdvanced.UnitTesting.Twitter.models.Message;
import JavaOOPAdvanced.UnitTesting.Twitter.models.MicrowaveOven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MicrowaveOvenTest {
    private MicrowaveOven microwaveOven;
    private Tweet message;

    @Before
    public void init() {
        this.microwaveOven = new MicrowaveOven();
        this.message = new Message();
    }

    @Test
    public void testReceive() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        this.microwaveOven.receive(this.message);

        Assert.assertEquals("Message was not printed!", this.microwaveOven.getMessage() + System.lineSeparator(), outContent.toString());
        Assert.assertTrue("Message was not added to the server!", this.microwaveOven.getServer().getMessages().size() == 1);
    }
}
