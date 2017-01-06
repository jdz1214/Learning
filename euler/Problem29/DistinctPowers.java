package Problem29;

import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Duncan on 1/4/2017.
 */
public class DistinctPowers {
    public static void main(String[] args) {
        distinctPowers();
    }

    public static void distinctPowers() {
        //ab for 2 ≤ a ≤ 100 and 2 ≤ b ≤ 100

        Set<BigInteger> set = new TreeSet<>();

        for (int a = 2; a <= 100; a++) {
            for (int b = 2; b <= 100; b++) {
                set.add(runEq(a, b));
            }
        }

        System.out.println("Distinct terms:\t" + set.size());
        int x = 0;
        for (BigInteger i : set) {
            if ( x % 10 == 0) { System.out.println(); }
            System.out.print(i + "\t");
            x++;
        }
    }

    public static BigInteger runEq(int a, int b) {
        return BigInteger.valueOf(a).pow(b);
    }
}
