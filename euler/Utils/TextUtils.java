package Utils;

import java.util.ArrayList;

/**
 * Created by Duncan on 1/13/2017.
 */
public class TextUtils {

    public static ArrayList<String> getPermutations(String string) {
        ArrayList<String> permutationList = new ArrayList<>();
        permute(permutationList, "", string);
        return permutationList;
    }

    private static void permute(ArrayList<String> permutationList, String prefix, String string) {
        int n = string.length();
        if (n == 0) { permutationList.add(prefix); }
        else {
            for (int i = 0; i < n; i++) {
                permute(permutationList, prefix + string.charAt(i), string.substring(0, i) + string.substring(i + 1, n));
            }
        }
    }

    public static ArrayList<String> getPrimeNumericalPermutations(int numDigits) {
        ArrayList<String> permutationList = new ArrayList<>();
        permuteNumericalPrimes(permutationList, "", numDigits);
        return permutationList;
    }

    private static void permuteNumericalPrimes(ArrayList<String> permutationList, String num, Integer numDigits) {
        if (num.length() == numDigits) { if (MathUtils.isPrime(Long.parseLong(num))) { permutationList.add(num); } }
        else {
            for (int i = 0; i <= 9; i++) {
                String s = String.valueOf(i);
                permuteNumericalPrimes(permutationList, num + s, numDigits);
            }
        }
    }
}
