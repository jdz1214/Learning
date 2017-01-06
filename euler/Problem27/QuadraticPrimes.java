package Problem27;

/**
 * Created by Duncan on 1/4/2017.
 */
public class QuadraticPrimes {
    public static void main(String[] args) {
        quadraticPrimes();
        //testQuad();
    }

    public static void quadraticPrimes() {
        int aMax = 0;
        int bMax = 0;
        int consecutivePrimes = 0;

        for (int a = -999; Math.abs(a) < 1000; a++) {
            for (int b = -1000; Math.abs(b) <= 1000; b++) {
                int n = 0;
                while (isPrime(quadraticEq(a, b, n))) {
                    n++; }
                //System.out.println("supposedly not prime?: " + quadraticEq(a,b,n) + " " + isPrime(quadraticEq(a,b,n)) + " a: " + a + " b: " + b + " n: " + n);
                if (n > consecutivePrimes) {
                    //System.out.println("maxPrimes:\t" + n + " a: " + a + " b: " + b);
                    consecutivePrimes = n; aMax = a; bMax = b; }
            }
        }

        System.out.println("Product of a*b of eq with max primes:\t" + aMax*bMax + "\t aMax: " + aMax + " bMax: " + bMax + " maxPrimes: " + consecutivePrimes);
    }

    public static void testQuad() {
        int a = -999;
        int b = 61;
        int n = 0;

        while(isPrime(quadraticEq(a,b,n))) {
            System.out.println("a: " + a + " b: " + b + " n: " + n + "\tquadEq: " + quadraticEq(a,b,n) + " isPrime: " + isPrime(quadraticEq(a,b,n)));
            n++;
        }
    }

    public static int quadraticEq(int a, int b, int n) {
        //n^2 + an + b. abs(a) < 1000 && abs(b) <= 1000. a and b can be negative numbers. Find a*b for the eq that produces
        //the most consecutive primes for values of n starting at zero.
        if (Math.abs(a) < 0 || Math.abs(a) >= 1000 || Math.abs(b) > 1000 || Math.abs(b) < 0) {
            try { throw new Exception() ; } catch (Exception e) { e.printStackTrace(); }
        }
        return n*n + a*n + b;
    }

    public static boolean isPrime(int n) {
        if (n < 0) { return false; }
        if( n % 2 == 0) { return false; }
        for(long i = 3; i <= Math.sqrt(n); i+=2) {
            if(n % i == 0) { return false; }
        }
        return true;
    }
}
