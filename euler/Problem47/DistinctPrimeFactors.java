package Problem47;

import Utils.MathUtils;

import java.util.Arrays;
import java.util.OptionalLong;

/**
 * Created by Duncan on 1/13/2017.
 */
public class DistinctPrimeFactors {

    public static void main(String[] args) {
        long digits = 4;
        long count = 0;
        long[] one = new long[]{};
        long[] two = new long[]{};
        long[] three = new long[]{};
        long[] four = new long[]{};
        boolean found = false;
        for (long i = 35000; i <= 10000000; i++) {
            one = hasDistinctPrimeFactors(i, digits, new long[]{});
            two = hasDistinctPrimeFactors(i + 1, digits, new long[]{});
            three = hasDistinctPrimeFactors(i + 2, digits, new long[]{});
            four = hasDistinctPrimeFactors(i + 3, digits, new long[]{});
            if (one.length >= 1 && two.length >= 1 && three.length >= 1 && four.length >= 1) { count = i; found = true; break; }
        }
        if (found) {
            System.out.println("Solution found:\t" + count);
            Arrays.stream(one).forEach(x -> System.out.print(x + "\t")); System.out.println("");
            Arrays.stream(two).forEach(x -> System.out.print(x + "\t")); System.out.println("");
            Arrays.stream(three).forEach(x -> System.out.print(x + "\t")); System.out.println("");
            Arrays.stream(four).forEach(x -> System.out.print(x + "\t")); System.out.println("");
        }
    }

    public static long[] hasDistinctPrimeFactors(long n, long distinctPrimeFactors, long[] array) {
        //System.out.println("n:\t" + n);
        long[] arr = MathUtils.getPrimeFactors(n);
        if (arr.length == distinctPrimeFactors) {
            long z = Arrays.stream(arr).reduce((x, y) -> x * y).getAsLong(); // Prime Factor Multiples
            if (z < n) {
                OptionalLong doubleDigit = Arrays.stream(arr).sorted().filter(a -> z * a == n).findFirst();
                if (doubleDigit.isPresent()) {
                    //Arrays.stream(arr).forEach(s -> System.out.print(s + "\t"));
                    //System.out.println("\t==\t" + n + "\t(using factor " + doubleDigit.getAsLong() + " twice.)");
                    return arr;
                }
            } else if (z == n) {
                //Arrays.stream(arr).forEach(r -> System.out.print(r + "\t"));
                //System.out.println("\t==\t" + n);
                return arr;
            }
        }
        return new long[]{};
    }
}