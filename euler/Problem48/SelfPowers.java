package Problem48;

import java.math.BigInteger;

/**
 * Created by Duncan on 1/13/2017.
 */
public class SelfPowers {

    public static void main(String[] args) {
        BigInteger bigInt = BigInteger.valueOf(0);

        for (long i = 1; i <= 1000; i++) {
            bigInt = bigInt.add(getPower(BigInteger.valueOf(i)));
        }
        System.out.println("BigString:\t" + bigInt.toString());
        System.out.println("Last ten digits:\t" + bigInt.toString().substring(bigInt.toString().length() - 10));
    }

    public static BigInteger getPower(BigInteger n) {
        return n.pow(n.intValue());
    }
}
