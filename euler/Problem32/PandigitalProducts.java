package Problem32;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Duncan on 1/6/2017.
 */
public class PandigitalProducts {
    public static void main(String[] args) {
        pandigitalProducts();
    }

    public static void pandigitalProducts() {
        Long sum = 0L;
        Set<Long> list = generateMultiplierMultiplicand();
        for (Long l : list) { sum += l; }
        System.out.println("Sum of products:\t" + sum);
    }

    public static Set<Long> generateMultiplierMultiplicand() {
        Set<Long> productList = new HashSet<>();

        for (long multiplier = 1; multiplier <= 123; multiplier++) {
            for (long multiplicand = 1; String.valueOf(multiplicand*multiplier).length() + String.valueOf(multiplicand).length() + String.valueOf(multiplier).length() <= 9; multiplicand++) {
                long product = multiplicand * multiplier;
                String prodStr = String.valueOf(product);
                String mOne = String.valueOf(multiplicand);
                String mTwo = String.valueOf(multiplier);
                String bigStr = prodStr + mOne + mTwo;
                if (isPandigital(bigStr)) { productList.add(product); System.out.println("product added:\t" + prodStr
                 + "\tMultiplier:\t" + mTwo + "\tMultiplicand:\t" + mOne);}
            }
        }

        return productList;
    }

    public static boolean isPandigital (String str) {
        if (str.replaceAll("0", "").length() != 9) { return false; }
        Set<Integer> set = str.chars().map(Character::getNumericValue).boxed().collect(Collectors.toSet());
        return set.size() == 9;
    }
}
