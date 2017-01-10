package Problem43;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Created by Duncan on 1/9/2017.
 */
public class SubstringDivisibility {
    private static ArrayList<String> pandigitalPermutations = new ArrayList<>();

    public static void main(String[] args) {
        subDivis();
    }

    public static void subDivis() {
        generatePandigitals("", "0123456789");
        BigInteger bigInt = pandigitalPermutations.stream().filter(SubstringDivisibility::hasDivisibleProperty).map(Long::parseLong).map(BigInteger::valueOf).reduce(BigInteger::add).get();
        System.out.println("Sum of specially divisible 0-9 pandigitals:\t" + bigInt.toString());
    }

    public static void generatePandigitals(String prefix, String str) {
        int n = str.length();
        if (n == 0) { pandigitalPermutations.add(prefix); }
        else {
            for (int i = 0; i < n; i++) {
                generatePandigitals(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
            }
        }
    }

    public static boolean hasDivisibleProperty(String str) {
        String[] subs = IntStream.range(1, 8).mapToObj(x -> str.substring(x, x+3)).toArray(String[]::new);
        int[] primes = new int[]{2,3,5,7,11,13,17};
        for (int i = 0; i < 7; i++) {
            if (Long.parseLong(subs[i]) % primes[i] != 0) { return false; }
        }
        return true;
    }
}
