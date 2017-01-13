package Utils;

/**
 * Created by Duncan on 1/13/2017.
 */
public class MathUtils {

    public static boolean isPrime(long n) {
        if (n == 2) { return true; }
        if (n < 2 || n % 2 == 0) { return false; }
        for (long i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) { return false; }
        }
        return true;
    }

    public static long generateNextPrime(long n) {
        if (n < 2) { return 2; }
        n = n % 2 == 0 ? n + 1 : n + 2;
        if (isPrime(n)) { return n; }
        return generateNextPrime(n);
    }
}
