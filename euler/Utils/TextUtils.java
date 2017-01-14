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
}
