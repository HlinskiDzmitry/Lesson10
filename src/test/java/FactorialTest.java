import org.testng.Assert;
import org.testng.annotations.Test;

public class FactorialTest {
    @Test
    public void testFactorialZero() {
        Assert.assertEquals(Factorial.factorial(0), 1);
    }

    @Test
    public void testFactorialPositiveNumber() {
        Assert.assertEquals(Factorial.factorial(6), 720);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialNegativeNumber() {
        Factorial.factorial(-1);
    }
}