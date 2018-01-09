package Utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jdz on 1/8/18.
 */
public class NewUtes {

    public static Set<String> generatePermutations(String input) {
        Set<String> set = new HashSet<>();

        if (input == "") {
            return set;
        }

        Character a = input.charAt(0);

        if (input.length() > 1) {
            input = input.substring(1);
            Set<String> permSet = generatePermutations(input);

            for (String x : permSet) {
                for (int i = 0; i <= x.length(); i++) {
                    set.add(x.substring(0, i) + a + x.substring(i));
                }
            }
        } else {
            set.add(a + "");
        }
        return set;
    }

}
