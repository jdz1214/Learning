package Problem38;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by jdz on 1/6/17.
 */
public class PandigitalMultiples {

    public static void main(String[] args) {
        //largestPandigitalConcatenatedProduct();
        testPermutations();
    }

    public static void testPermutations() {
        String pandigitMax = "987654321";
        while (true) {
            System.out.println("pandigitMax:\t" + pandigitMax);
            if (pandigitMax.equals("123456789")) { break; }
            pandigitMax = reversePermute(pandigitMax);
        }
    }

    public static void largestPandigitalConcatenatedProduct() {
        String pandigital = "987654321";
        boolean found = false;
        while (true) {
            if (pandigital.equals("918273645")) { System.out.println("Found 918273645."); }
            //System.out.println("Permutation:\t" + pandigital);
            if (pandigital.equals("123456789")) { break; }
            if (canConcatenate(pandigital)) { found = true; break; }
            pandigital = reversePermute(pandigital);
        }

        if (found) { System.out.println("Largest 9-digit pandigital number formable via concatenation:\t" + pandigital); }
        else { System.out.println("No pandigital number formable via concatenation was found."); }
    }

    public static boolean canConcatenate(String nineDigitPandigital) {

        arrayloop:
        for (int n = 1; n <= 100; n++) {
            int[] arr = IntStream.range(1, n+1).toArray();
            integerloop:
            for (long j = 1L; j < 1000; j++) {
                String result = getConcatenatedProducts(arr, j);
                if (result.length() > 9 || Long.parseLong(result) > Long.parseLong(nineDigitPandigital)) { break; }
                System.out.println("getConcProd:\t" + getConcatenatedProducts(arr, j) + "\tnineDigitPandital:\t" + nineDigitPandigital);
                if (getConcatenatedProducts(arr, j).equals(nineDigitPandigital)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getConcatenatedProducts(int[] multiplierArray, long multiplicand) {
        return Arrays.stream(multiplierArray).mapToLong(x -> x * multiplicand).mapToObj(String::valueOf).reduce(String::concat).get();
    }

    public static boolean isPandigital (String str) {
        if (str.replaceAll("0", "").length() != 9) { return false; }
        Set<Integer> set = str.chars().map(Character::getNumericValue).boxed().collect(Collectors.toSet());
        return set.size() == 9;
    }

    public static String reversePermute(String str) {
        //ReversePermutation: Find pivot (two adjacent digits where left is greater in value than right). Swap them.
        //Then, reorder to the max value all digits to the right of j. Correct?
        //4321 -> pivot = [2,1]: 4312 -> pivot = [3,1]: 4132 reorder digits? yes. -> pivot = [3,2]: 4123
        //987654321 pivot [2,1]: 987654312 -> pivot [3,1]: 987654132 -> pivot [3,2]: 987654123 -> pivot [4,1]
        //Need to swap the left part of pivot[] with the largest digit to the right of it.
        for (int i = str.length() - 1; i > 0; i--) {
            int j = i - 1;
            char right = str.charAt(i);
            char left = str.charAt(j);
            if (Character.getNumericValue(left) > Character.getNumericValue(right)) {
                str = swap(str, j);
                break;
            }
        }
        if (!isPandigital(str)) { System.out.println("Pandigital issue with " + str); }
        return str;
    }

    public static String swap(String str, int i) {
        char[] strArr = str.substring(0, i+1).toCharArray();
        char temp = strArr[i];
        int max = str.substring(i+1).chars().map(Character::getNumericValue).max().getAsInt();
        char[] endArr = str.substring(i+1).toCharArray();
        for (int c = 0; c < endArr.length; c++) {
            if (Character.getNumericValue(endArr[c]) == max) { strArr[i] = endArr[c]; endArr[c] = temp; break; }
        }
        String s = IntStream.range(0, endArr.length).boxed()
                .map(c -> Character.getNumericValue(endArr[c]))
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                .reduce(String::concat)
                .get();
        return new String(strArr) + s;
    }

}

//What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?
//Could start by reverse-permutating pandigital strings, starting with the largest, and iterating until the first that can be formed as
//a concatenated product is found.