package Problem36;

import java.util.stream.LongStream;

/**
 * Created by jdz on 1/6/17.
 */
public class DoubleBasePalindromes {

    public static void main(String[] args) {
        doubleBasePalindromes();
    }

    public static void doubleBasePalindromes() {
        System.out.println("sum of all double-base palindromes:\t" + LongStream.range(1,1000000)
                .filter(DoubleBasePalindromes::isPalindrome)
                .filter(x -> isPalindrome(Integer.toBinaryString((int) x)))
                .sum());
    }

    public static boolean isPalindrome(Long l) {
        String str = String.valueOf(l);
        if (str.length() == 1) { return true; }

        if (str.length() % 2 == 0) {
            String first = str.substring(0, str.length() / 2);
            String last = str.substring(str.length() / 2);
            String lastRev = reverse(last);
            return first.equals(lastRev);
        }

        String first = str.substring(0, str.length() / 2);
        String last = str.substring((str.length() + 1) / 2);
        String revLast = reverse(last);
        return first.equals(revLast);
    }

    public static boolean isPalindrome(String str) {
        if (str.length() == 1) { return true; }

        if (str.length() % 2 == 0) {
            String first = str.substring(0, str.length() / 2);
            String last = str.substring(str.length() / 2);
            String lastRev = reverse(last);
            return first.equals(lastRev);
        }

        String first = str.substring(0, str.length() / 2);
        String last = str.substring((str.length() + 1) / 2);
        String revLast = reverse(last);
        return first.equals(revLast);
    }

    public static String reverse(String str) {
        char[] arr = str.toCharArray();
        String newStr = "";
        for (int i = arr.length - 1; i >= 0; i--) {
            newStr = newStr + arr[i];
        }
        return newStr;
    }
}
