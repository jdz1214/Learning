package Problem47;

import Utils.MathUtils;

import java.util.Arrays;
import java.util.OptionalLong;
import java.util.stream.LongStream;

/**
 * Created by Duncan on 1/13/2017.
 */
public class DistinctPrimeFactors {

    public static void main(String[] args) {
        //hasDistinctPrimeFactors(644, 3);
        long count = 644;
        long digits = 4;
        long[] consecutivePrimes = hasDistinctPrimeFactors(count, digits, new long[]{});

        System.out.println("Solution found:\t" + consecutivePrimes[0]);
        System.out.println("First number in sequence:\t" + count);

    }

    public static long[] hasDistinctPrimeFactors(long n, long distinctPrimeFactors, long[] array) {
        System.out.println("n:\t" + n);
        if (array.length == 4) { return array; }
        long[] arr = MathUtils.getPrimeFactors(n);
        if (arr.length == distinctPrimeFactors) {
            long z = Arrays.stream(arr).reduce((x, y) -> x * y).getAsLong(); // Prime Factor Multiples
            if (z < n) {
                OptionalLong doubleDigit = Arrays.stream(arr).sorted().filter(a -> z * a == n).findFirst();
                if (doubleDigit.isPresent()) {
                    //Arrays.stream(arr).forEach(s -> System.out.print(s + "\t"));
                    //System.out.println("\t==\t" + n + "\t(using factor " + doubleDigit.getAsLong() + " twice.)");
                    long[] newArray = LongStream.concat(Arrays.stream(array), LongStream.of(n)).toArray();
                    return hasDistinctPrimeFactors(n + 1, distinctPrimeFactors, newArray);
                }
            } else if (z == n) {
                //Arrays.stream(arr).forEach(r -> System.out.print(r + "\t"));
                //System.out.println("\t==\t" + n);
                long[] newArray = LongStream.concat(Arrays.stream(array), LongStream.of(n)).toArray();
                return hasDistinctPrimeFactors(n + 1, distinctPrimeFactors, newArray);
            }
        }
        return hasDistinctPrimeFactors(n + 1, distinctPrimeFactors, new long[]{});
    }
}


//    public static boolean hasDistinctPrimeFactors(long n) {
//        long[] arr = MathUtils.getPrimeFactors(n);
//        Predicate<Long> d1 = d -> {
//            if (d < n) {
//                Arrays.stream(arr).filter(a -> d * a == n).findFirst().ifPresent(b -> {
//                    Arrays.stream(arr).forEach(s -> System.out.print(s + "\t"));
//                    System.out.print("\tUse factor " + b + " twice.");
//                });
//            }
//            return false;
//        };
//
//
//
//
//
//        Arrays.stream(arr).reduce((x , y) -> x * y).ifPresent(z -> {
//            if (z < n) {
//                return Arrays.stream(arr).filter(a -> z * a == n).allMatch(d1).findFirst();
//            } else if (z == n) { Arrays.stream(arr).forEach(r -> System.out.print(r + "\t")); System.out.print("\t == " + n); }
//        });
//    }