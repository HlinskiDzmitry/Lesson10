import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class FactorialTest {

    @Test
    public void testFactorialZero() {
        assertEquals(1, Factorial.factorial(0), "Факториал 0 должен быть равен 1");
    }

    @Test
    public void testFactorialPositiveNumber() {
        assertEquals(720, Factorial.factorial(6), "Факториал 6 должен быть равен 720");
    }

    @Test
    public void testFactorialNegativeNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Factorial.factorial(-1));
        assertEquals("Число должно быть положительным", exception.getMessage());
    }
}