package Problem33;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Duncan on 1/6/2017.
 */
public class DigitCancellingFractions {
    public static Map<Double, Double> foundFractions = new HashMap<>();

    public static void main(String[] args) {
        findFract();
    }

    public static void findFract() {
        for (double denominator = 11.0; denominator <= 99.0; denominator++) {
            for (double numerator = 10.0; numerator < denominator; numerator++) {
                if ((numerator % 10 == 0 && denominator % 10 == 0)) { continue; }
                if (checkFraction(numerator, denominator)) { System.out.println("Found fraction."); }
            }
        }

        if (foundFractions.entrySet().size() > 0) {
            reduceFractions(foundFractions);
        } else {
            System.out.println("No fractions found.");
        }
    }

    public static boolean checkFraction(double numerator, double denominator) {
        ArrayList<Integer> numer = String.valueOf((int) numerator).chars().map(Character::getNumericValue).boxed().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> denom = String.valueOf((int) denominator).chars().map(Character::getNumericValue).boxed().collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(numer);
        Collections.sort(denom);
        for (int d : denom) {
            double dbl = (double) d;
            for (int n : numer) {
                double nbl = (double) n;
                if ((nbl / dbl) == (numerator / denominator)) {
                    int otherNumerator = n == numer.get(0) ? numer.get(1) : numer.get(0);
                    int otherDenominator = d == denom.get(0) ? denom.get(1) : denom.get(0);
                    if (otherNumerator == otherDenominator) {
                        foundFractions.put(numerator, denominator);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void reduceFractions(Map<Double, Double> map) {
        int[] numVals = map.keySet().stream().mapToInt(Double::intValue).toArray();
        int[] denomVals = map.values().stream().mapToInt(Double::intValue).toArray();

        int numerProduct = Arrays.stream(numVals).reduce(1, (x, y) -> x * y);
        System.out.println("numerProduct:\t" + numerProduct);
        int denomProduct = Arrays.stream(denomVals).reduce(1, (x, y) -> x * y);
        System.out.println("denomProduct:\t" + denomProduct);

        boolean reduced = false;
        for (int i = numerProduct; i > 1; i--) {
            if (numerProduct % i == 0 && denomProduct % i == 0) {
                System.out.println("reduced fraction:\t" + (numerProduct / i) + "/" + (denomProduct / i));
                reduced = true;
                break;
            }
        }

        if (!reduced) {
            System.out.println("Unreducable fraction:\t" + numerProduct + "/" + denomProduct);
        }
    }

}
