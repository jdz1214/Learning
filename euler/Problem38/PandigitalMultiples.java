package Problem38;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jdz on 1/6/17.
 */
public class PandigitalMultiples {

    public static void main(String[] args) {

    }

    public static void largestPandigitalConcatenatedProduct() {
        long i = 1L;
        int[] n = new int[]{1};
        while (getConcatenatedProducts(n, i).length() < 10) {

        }
    }

    public static String getConcatenatedProducts(int[] multiplierArray, long multiplicand) {
        return Arrays.stream(multiplierArray).mapToLong(x -> x * multiplicand).mapToObj(String::valueOf).reduce(String::concat).get();
    }

    public static boolean isPandigital (String str) {
        if (str.replaceAll("0", "").length() != 9) { return false; }
        Set<Integer> set = str.chars().map(Character::getNumericValue).boxed().collect(Collectors.toSet());
        return set.size() == 9;
    }

}


//What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?