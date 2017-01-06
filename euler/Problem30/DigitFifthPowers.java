package Problem30;

import java.util.ArrayList;

/**
 * Created by Duncan on 1/4/2017.
 */
public class DigitFifthPowers {
    public static void main(String[] args) {
        digitFifthPowers();
    }

    public static void digitFifthPowers() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i < 1000000; i++) {
            if (getSumDigitsToFifth(i) == i) { list.add(i); }
        }
        System.out.println("sum:\t" + list.stream().mapToInt(Integer::intValue).sum());
    }

    public static int getSumDigitsToFifth(long l) {
        String str = String.valueOf(l);
        int sum = str.chars()
                .map(Character::getNumericValue)
                .map(x -> (int) Math.pow(x, 5.0)).sum();
        return sum;
    }
}