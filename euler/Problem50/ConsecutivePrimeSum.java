package Problem50;

import Utils.MathUtils;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Created by Duncan on 1/13/2017.
 */
public class ConsecutivePrimeSum {

    public static void main(String[] args) {
        primeSum();
    }

    public static void primeSum() {
        //generate a list of all the primes under a million
        ArrayList<Long> primes = MathUtils.generatePrimeRange(1, 1000000);

        long maxPrime = 0;

        for (int i = 0; i < primes.size() - 1; i++) {
            for (int j = i + 1; j <= primes.size(); j++) {
                long sum = IntStream.range(i, j).mapToLong(primes::get).sum();
                if (MathUtils.isPrime(sum) && sum > maxPrime) {
                    maxPrime = sum;
                }
            }
        }
        System.out.println("MaxPrime:\t" + maxPrime);
    }

}
