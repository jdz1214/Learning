package Problem37;

/**
 * Created by Duncan on 1/7/2017.
 */
public class TruncatablePrimes {

    public static void main(String[] args) {
        findTruncatable();
    }

    public static void findTruncatable() {
        long sum = 0L;
        int count = 0;

        for (int i = 9; i < 1000000 ; i+=2) {
            if (isPrime(i)) {
                if (truncatableLeft(i) && truncatableRight(i)) {
                    sum += i;
                    count++;
                    System.out.println("i:\t" + i);
                }
            }
        }
        System.out.println("Found " + count + " primes. Sum of truncatable primes:\t" + sum);
    }

    public static boolean truncatableLeft(long n) {
        String str = String.valueOf(n);
        if (str.length() == 1) { return isPrime(n); }

        return isPrime(n) && truncatableLeft(Long.parseLong(str.substring(1)));
    }

    public static boolean truncatableRight(long n) {
        String str = String.valueOf(n);
        if (str.length() == 1) { return isPrime(n); }

        return isPrime(n) && truncatableRight (Long.parseLong(str.substring(0, str.length()-1)));
    }

    public static boolean isPrime(long n) {
        if (n == 1) { return false; }
        if (n == 2) { return true; }
        if (n % 2 == 0) { return false; }
        for (long i = 3; i <= Math.sqrt(n); i+=2) {
            if (n % i == 0) { return false; }
        }
        return true;
    }
}
