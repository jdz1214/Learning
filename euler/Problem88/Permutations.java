package Problem88;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Duncan on 1/11/2017.
 */
public class Permutations {
    private static Set<Integer> set = new HashSet<>();
    private static boolean found = false;
    public static void main(String[] args) {
        //initializer(3);
        //justLoops(3);
        for (int i = 2; i <= 12000; i++) {
            found = false;
            recursiveVersion(0, i, "");
            System.out.println("Completed k:\t" + i);
        }
        System.out.println("Sum:\t" + set.stream().mapToInt(Integer::intValue).sum());

    }

    public static void recursiveVersion(int start, int k, String string) {
        if (!found) {
            if (start == k) {
                if (sum(string) == prod(string)) {
                    found = true;
                    set.add(sum(string));
                }
            } else {
                for (int i = 0; i < k; i++) {
                    recursiveVersion(start + 1, k, string + String.valueOf(i + 1) + "\t");
                }
            }
        }
    }

    public static int sum(String string) {
        return Arrays.stream(string.split("\t")).mapToInt(Integer::parseInt).sum();
    }

    public static int prod(String string) {
        return Arrays.stream(string.split("\t")).mapToInt(Integer::parseInt).reduce((x,y) -> x*y).getAsInt();
    }
}
