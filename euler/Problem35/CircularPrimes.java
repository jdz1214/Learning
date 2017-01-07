package Problem35;

import java.util.stream.LongStream;

/**
 * Created by Duncan on 1/6/2017.
 */
public class CircularPrimes {

    public static void main(String[] args) {
        circularPrimes();
    }

    public static void circularPrimes() {
        Long count = LongStream.range(1, 1000001)
                .filter(CircularPrimes::isPrime)
                .filter(CircularPrimes::isCircular)
                .boxed()
                .count();
        System.out.println("Number of circular primes below one million:\t" + count);
    }

    public static boolean isPrime(long n) {
        if( n % 2 == 0) { return false; }
        for(long i = 3; i <= Math.sqrt(n); i+=2) {
            if(n % i == 0) { return false; }
        }
        return true;
    }

    private static boolean isCircular(Long prime) {
        String p = String.valueOf(prime);
        for (int i = 0; i < String.valueOf(prime).length(); i++) {
            p = swap(p);
            if (!isPrime(Long.parseLong(p))) { return false; }
        }
        return true;
    }

    private static String swap(String str) {
        return str.substring(1) + str.charAt(0);
    }
}
