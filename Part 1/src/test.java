import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class test {
    private App s;
    @Before
    public void setUp() {
        s = new App();
    }

    @Test
    public void testPrint() {
        String expectedOutput = "result";
        String res = s.ret();
        Assert.assertEquals(res, expectedOutput);
    }

    @After
    public void tearDown() {
        s = null;
    }
}
