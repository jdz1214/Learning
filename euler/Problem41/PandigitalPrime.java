package Problem41;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

/**
 * Created by Duncan on 1/9/2017.
 */
public class PandigitalPrime {
    private static ArrayList<Long> pandigitalPrimePermutations = new ArrayList<>();

    public static void main(String[] args) {
        findLargestPandigitalPrime("987654321");
    }

    public static void findLargestPandigitalPrime(String str) {
        if (is1ToNPandigital(Long.parseLong(str))) {
            generatePandigitalPermutations("", str);
            pandigitalPrimePermutations.sort(Comparator.reverseOrder());
            if (pandigitalPrimePermutations.size() > 0) {
                System.out.println("Largest pandigital permutation:\t" + pandigitalPrimePermutations.get(0));
            } else {
                if (str.length() > 2) {
                    pandigitalPrimePermutations = new ArrayList<>();
                    str.chars().map(Character::getNumericValue).boxed().sorted(Comparator.reverseOrder()).skip(1).map(String::valueOf).reduce(String::concat).ifPresent(PandigitalPrime::findLargestPandigitalPrime);
                } else {
                    System.out.println("No further action possible.");
                }
            }
        } else {
            System.out.println("You have entered a string with is not 1-n pandigital. Please try a different string.");
        }
    }

    public static boolean is1ToNPandigital(long n) {
        Optional<String> ordered = String.valueOf(n).chars().map(Character::getNumericValue).boxed().sorted().map(String::valueOf).reduce(String::concat);
        if(ordered.isPresent()) {
            String orderedStr = ordered.get();
            for (int i = 0; i < orderedStr.length(); i++) {
                if (Character.getNumericValue(orderedStr.charAt(i)) != i+1) { return false; }
            }
            return true;
        }
        return false;
    }

    public static void generatePandigitalPermutations(String prefix, String str) {
        int n = str.length();
        if (n == 0 && isPrime(Long.parseLong(prefix))) { pandigitalPrimePermutations.add(Long.parseLong(prefix)); }
        else {
            for (int i = 0; i < n; i++) {
                generatePandigitalPermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
            }
        }
    }


    public static boolean isPrime(Long n) {
        if (n == 2) { return true; }
        if (n == 1 || n % 2 == 0) { return false; }

        for (long i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) { return false; }
        }

        return true;
    }

}
