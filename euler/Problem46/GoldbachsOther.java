package Problem46;

import Utils.MathUtils;

/**
 * Created by Duncan on 1/13/2017.
 */
public class GoldbachsOther {

    public static void main(String[] args) {
        goldbach(1);
    }

    public static void goldbach(long n) {
        while (n <= 2 || n % 2 == 0 || MathUtils.isPrime(n)) { n += 1; }
        long nextPrime = MathUtils.generateNextPrime(n);

        boolean found = false;
        for (long i = n; i < nextPrime; i += 2) {
            if (!isPrimeSquare(i)) { System.out.println("Next odd composite that violates Goldbach's Other Conjecture:\t" + i); found = true; break;}
        }

        if (!found) { goldbach(nextPrime + 1); }
    }

    public static boolean isPrimeSquare(long n) {
        //n must not be a prime, must be odd, and must be positive.
        //if a number can be written as the sum of a prime and a square*2, return true.
        //return prime + x/2 (where x is y*y)
        //remove subtract 2xsquared, return isPrime.
        if (MathUtils.isPrime(n) || n % 2.0 == 0.0 || n < 0) { return false; }
        long primeCounter = 1;
        while (true) {
            long prime = MathUtils.generateNextPrime(primeCounter);
            if (n - prime < 2) { break; }
            long squaredDouble = n - prime;
            double squared = (double) squaredDouble / 2.0;
            if ( squared % 1.0 == 0.0) {
                if (Math.sqrt(squared) % 1.0 == 0.0) {
                    return true;
                }
            }
            primeCounter++;
        }
        return false;
    }


}
