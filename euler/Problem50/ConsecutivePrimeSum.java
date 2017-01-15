package Problem50;

import Utils.MathUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
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
        System.out.println("primes.size():\t" + primes.size());
        long maxPrimeCount = 0;
        long maxPrime = 0;
        ArrayList<Long> maxPrimeVals = new ArrayList<>();
        long ceiling = Collections.max(primes);
        long floor = Collections.min(primes);

        for (int i = 1; i < primes.size() - 1; i++) {
            for (int j = i + 1; j <= primes.size(); j++) {
                long sum = IntStream.range(i, j).mapToLong(primes::get).sum();
                System.out.println("sum:\t" + sum + "\ti:\t" + i + "\tj:\t" + j);

                if (sum > ceiling) { break; }
                if (MathUtils.isPrime(sum) && j - i > maxPrimeCount) {
                    maxPrimeCount = j - i;
                    maxPrime = sum;
                    maxPrimeVals = IntStream.range(i, j).mapToLong(primes::get).boxed().collect(Collectors.toCollection(ArrayList::new));
                    System.out.println("");
                }
            }
        }
        System.out.println("maxPrime:\t" + maxPrime + "\tnumber of consecutive summing primes:\t" + maxPrimeCount);
    }

}
