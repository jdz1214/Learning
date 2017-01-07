package Problem34;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Duncan on 1/6/2017.
 */
public class DigitFactorials {

    public static void main(String[] args) {
        findFactorials();
    }

    public static void findFactorials() {
        BigInteger sum = BigInteger.valueOf(0);
        for (int i = 3; i < 100001; i++) {
            if (getSumFactorialOfDigits(i).longValue() == i) {
                sum = sum.add(getSumFactorialOfDigits(i));
            }
        }
        System.out.println("Sum:\t" + sum);
    }

    public static BigInteger getSumFactorialOfDigits(long l) {
        int[] arr = String.valueOf(l).chars().map(Character::getNumericValue).toArray();
        ArrayList<BigInteger> list = Arrays.stream(arr).boxed().map(BigInteger::valueOf).collect(Collectors.toCollection(ArrayList::new));
        BigInteger sum = BigInteger.valueOf(0);
        for (BigInteger bi : list) {
            sum = sum.add(getFactorial(bi));
        }
        return sum;
    }

    public static BigInteger getFactorial(BigInteger l) {
        BigInteger prod = BigInteger.valueOf(1);
        for (long i = l.longValue(); i > 0; i--) {
            prod = prod.multiply(BigInteger.valueOf(i));
        }
        return prod;
    }
}
