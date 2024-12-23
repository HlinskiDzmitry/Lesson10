public class Factorial {
    public static long factorial(int f) {
        if (f < 0) {
            throw new IllegalArgumentException("Число должно быть положительным");
        }
        long result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(factorial(1));
    }
}
