package Utils;

import java.util.ArrayList;
import java.util.stream.LongStream;

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

    public static ArrayList<Long> generatePrimeRange(long lowerInclusive, long upperExclusive) {
        ArrayList<Long> list = new ArrayList<>();

        if (lowerInclusive >= upperExclusive) { return list; }
        if (lowerInclusive < 2) { lowerInclusive = 2; }
        if (lowerInclusive == 2) {
            list.add(2L);
            lowerInclusive++;
        }

        for (long i = lowerInclusive; i < upperExclusive; i++){
            if (isPrime(i)) { list.add(i); }
        }
        return list;
    }

    public static long[] getFactors(long n) {
        if (n < 1) { return new long[]{}; }
        if (isPrime(n)) { return new long[] {1, n}; }
        return LongStream.concat(LongStream.range(1, n / 2 + 1), LongStream.of(n)).filter(x -> n % x == 0.0).distinct().toArray();
    }

    public static long[] getPrimeFactors(long n) {
        if (n < 2) { return new long[]{}; }
        if (isPrime(n)) { return new long[] {1, n}; }
        return LongStream.concat(LongStream.range(1, n / 2 + 1), LongStream.of(n)).filter(x -> n % x == 0.0 && isPrime(x)).distinct().toArray();
    }

    public static ArrayList<Long> primeSieve(int n) {

        // initially assume all integers are prime
        boolean[] isPrime = new boolean[n+1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }


        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 2; factor*factor <= n; factor++) {

            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider mutiples factor, factor+1, ...,  n/factor
            if (isPrime[factor]) {
                for (int j = factor; factor*j <= n; j++) {
                    isPrime[factor*j] = false;
                }
            }
        }
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i]) { list.add((long) i); }
        }

        return list;
    }
}
