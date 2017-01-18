package Problem51;

import Utils.MathUtils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Duncan on 1/17/2017.
 */
public class PrimeDigitReplacements {

    public static void main(String[] args) {
        primeDigitReplacements(8);
    }

    public static void primeDigitReplacements(int familySize) {
        int numDigits = 3;

        boolean found = false;
        while (!found) {
            System.out.println("Running with " + numDigits);
            System.out.println("Generating base primes...");
            ArrayList<Long> basePrimes = generateBasePrimes(numDigits);
            System.out.println("Done generating base primes.");
            System.out.println("Finding prime families...");
            found = findFamily(basePrimes, familySize);
            System.out.println("Done finding prime families.");
            numDigits++;
        }
    }

    public static boolean findFamily(ArrayList<Long> basePrimes, int primeFamilySize) {
        for (int x = 0; x < basePrimes.size(); x++) {
            String basePrime = String.valueOf(basePrimes.get(x));
            System.out.println("basePrime:\t" + basePrime);
            ArrayList<int[]> startPoints = determineStartPoints(basePrime);
            ArrayList<String> primeFamily = new ArrayList<>();


            boolean found = false;
            for (int i = 0; i < startPoints.size(); i++) {
                int[] sp = startPoints.get(i);
                primeFamily = new ArrayList<>();
                permutePrimeFamily(primeFamily, basePrime, sp[0], sp[1]);
                if (primeFamily.size() >= primeFamilySize) {
                    System.out.println("primeFamily.size():\t" + primeFamily.size());
                    System.out.println("We've done it, Trinity. We've found him.");
                    found = true;
                    break;
                }
            }
            if (found) {
                primeFamily.forEach(y -> System.out.print(y + "\t")); System.out.println();
                System.out.println("First in series:\t" + basePrime);
                return true;
            }
        }
        System.out.println("No prime family of size " + primeFamilySize + " found.");
        return false;
    }

    private static ArrayList<int[]> determineStartPoints(String basePrime) { //returns a list of int[2] with int[0] == first and int[1] == second
        ArrayList<int[]> list = new ArrayList<>();
        int n = basePrime.length();
        int maxGap = n - 3; //one before the end because we don't modify the last digit, and the two points themselves.
        for (int i = 0; i < maxGap; i++) { //An iteration for each gap size
            int gap = i + 1;
            for (int k = 0; k < n - 1 - gap; k++) {
                char first = basePrime.charAt(k);
                char second = basePrime.charAt(k + gap);
                //System.out.println("basePrime:\t" + basePrime + "\tfirst:\t" + first + "\tsecond:\t" + second);
                if (first == second) {
                    int digit = Character.getNumericValue(first);
                    if (digit == 0 || digit == 1 || digit == 2) {
                        list.add(new int[]{k, k + gap});
                    }
                }
            }
        }
        return list;
    }

    private static void permutePrimeFamily(ArrayList<String> primeFamily, String basePrime, int first, int second) {
        if (basePrime.charAt(first) == basePrime.charAt(second)) {
            int nonPrimes = 0;
            for (int i = 0; i <= 9; i++) {
                if (nonPrimes > 3) { break; }
                String c = String.valueOf(i);
                String permute = basePrime.substring(0, first) + c + basePrime.substring(first + 1, second) + c + basePrime.substring(second + 1);
                int len = String.valueOf(Long.parseLong(permute)).length();
                if (len == basePrime.length() && MathUtils.isPrime(Long.parseLong(permute))) { primeFamily.add(permute); } else { nonPrimes++; }
            }
        } else { System.out.println("permutePrimeFamily failed equality test."); }
    }

    public static boolean isPotentialBasePrime(long potentialBasePrime) {
        if (!MathUtils.isPrime(potentialBasePrime)) { return false; }
        String string = String.valueOf(potentialBasePrime);
        Pattern pattern = Pattern.compile("([012]).*\\1");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    public static ArrayList<Long> generateBasePrimes(int numDigits) {
        // Rules:
        // last digit must be odd.
        // numbers must be prime.
        // numbers must contain pairs of either 0's, 1's, or 2's. So at least two 0's, 1's, or 2's.
        // Should permute the substring of the number to exclude the last digit.
        int subDigits = numDigits - 1;

        ArrayList<String> lastDigits = new ArrayList<>();
        for (int i = 1; i <= 9; i += 2) {
            lastDigits.add(String.valueOf(i));
        }

        String start = "";
        String end = "";

        for (int i = 0; i < numDigits; i++) {
            start = start + "1";
            end = end + "9";
        }
        long startLong = Long.parseLong(start);
        long endLong = Long.parseLong(end);

        ArrayList<Long> bPrimes = MathUtils.generatePrimeRange(startLong,endLong);
        ArrayList<Long> basePrimes = bPrimes.stream()
                .filter(s -> String.valueOf(s).length() == subDigits)
                .filter(PrimeDigitReplacements::isPotentialBasePrime)
                .collect(Collectors.toCollection(ArrayList::new));
        basePrimes.forEach(System.out::println);


        return basePrimes;
    }
}
