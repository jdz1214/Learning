package Problem38;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Created by jdz on 1/6/17.
 */
public class PandigitalMultiples {
    private static ArrayList<String> pandigitalPermutations = new ArrayList<>();

    public static void main(String[] args) {
        largestPandigitalConcatenatedProduct();
    }

    public static void largestPandigitalConcatenatedProduct() {
        generatePermutations("", "987654321");
        pandigitalPermutations.sort(Comparator.reverseOrder());
        Optional<String> answer = pandigitalPermutations.stream().filter(PandigitalMultiples::canConcatenate).findFirst();
        if (answer.isPresent()) {
            System.out.println("Largest concatably-generated pandigital:\t" + answer.get());
        } else {
            System.out.println("No concatable pandigital found.");
        }
    }

    public static boolean canConcatenate(String nineDigitPandigital) {

        arrayloop:
        for (int n = 1; n <= 100; n++) {
            int[] arr = IntStream.range(1, n+1).toArray();
            integerloop:
            for (long j = 1L; j < 10000; j++) {
                String result = getConcatenatedProducts(arr, j);
                if (result.length() > 9 || Long.parseLong(result) > Long.parseLong(nineDigitPandigital)) { break; }
                //System.out.println("getConcProd:\t" + getConcatenatedProducts(arr, j) + "\tnineDigitPandital:\t" + nineDigitPandigital);
                if (getConcatenatedProducts(arr, j).equals(nineDigitPandigital)) {
                    System.out.println("array from 1 to n, val of n:\t" + n + "\t integer:\t" + j);
                    return true;
                }
            }
        }
        return false;
    }

    public static String getConcatenatedProducts(int[] multiplierArray, long multiplicand) {
        return Arrays.stream(multiplierArray).mapToLong(x -> x * multiplicand).mapToObj(String::valueOf).reduce(String::concat).get();
    }

    public static void generatePermutations(String prefix, String str) {
        int n = str.length();
        if (n == 0 && Long.parseLong(prefix) >= 918273645L) { pandigitalPermutations.add(prefix); }
        else {
            for (int i = 0; i < n; i++)
                generatePermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }
}
