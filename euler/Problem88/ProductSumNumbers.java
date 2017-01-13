package Problem88;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Duncan on 1/11/2017.
 */
public class ProductSumNumbers {

    private static Map<Integer, Integer> intMap = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Sum of mins from 2 to 12000:\t" + findFirstProductSums());
    }

    public static int findFirstProductSums() {
        for (int i = 2; i <= 12000; i++) {
            int n = recursiveVersion(0, i, "");
            System.out.println("k:\t" + i + "\t\tn:\t" + n);
            intMap.put(i, n);
        }
        return intMap.values().stream().mapToInt(Integer::intValue).sum();
    }

    public static int recursiveVersion(int start, int k, String string) {
        if (start == k) { if (sum(string) == prod(string)) { System.out.println("Found " + sum(string) + " for k=" + k); return sum(string); } }
        else {
            for (int i = 0; i < k; i++) {
                return recursiveVersion(start + 1, k, string + String.valueOf(i+1) + "\t");
            }
        }
        return -1;
    }

    public static int sum(String string) {
        return Arrays.stream(string.split("\t")).mapToInt(Integer::parseInt).sum();
    }

    public static int prod(String string) {
        return Arrays.stream(string.split("\t")).mapToInt(Integer::parseInt).reduce((x,y) -> x*y).getAsInt();
    }
}
