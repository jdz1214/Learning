package Problem49;

import Utils.MathUtils;
import Utils.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by Duncan on 1/13/2017.
 */
public class PrimePermutations {

    public static void main(String[] args) {
        primePermutations();
    }

    public static void primePermutations() {
        ArrayList<Long> primes = MathUtils.generatePrimeRange(1111,9999);
        ArrayList<ArrayList<Long>> primePermutations = new ArrayList<>();
        for (Long prime : primes) {
            ArrayList<String> list = TextUtils.getPermutations(String.valueOf(prime));
            ArrayList<Long> filterList = list.stream().map(Long::parseLong).filter(x -> String.valueOf(x).length() == 4).filter(MathUtils::isPrime).distinct().collect(Collectors.toCollection(ArrayList<Long>::new));
            if (filterList.size() >= 3) { primePermutations.add(filterList); }
        }
        for (ArrayList<Long> primeList : primePermutations) {
            for (int i = 0; i < primeList.size() - 3; i++) {
                if (Math.abs(primeList.get(i) - primeList.get(i + 1)) == Math.abs(primeList.get(i + 1) - primeList.get(i + 2))) {
                    ArrayList<Long> sequence = new ArrayList<>();
                    sequence.add(primeList.get(i)); sequence.add(primeList.get(i + 1)); sequence.add(primeList.get(i + 2));
                    Collections.sort(sequence);
                    System.out.println("Found sequence:\t" + sequence.get(i) + " " + sequence.get(i + 1) + " " + sequence.get(i + 2));
                }
            }
        }
    }

    public static ArrayList<Long> generatePrimes(int digits) {
        String start = "";
        String end = "";
        for (int i = 0; i < digits; i++) {
            start = start + "1";
            end = end + "9";
        }
        Integer startInt = Integer.parseInt(start);
        Integer endInt = Integer.parseInt(end);

        return MathUtils.generatePrimeRange(startInt, endInt + 1);
    }
}
