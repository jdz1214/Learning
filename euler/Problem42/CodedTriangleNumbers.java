package Problem42;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Duncan on 1/9/2017.
 */
public class CodedTriangleNumbers {

    public static void main(String[] args) {
        findCodedTriangleNumbers();
    }

    public static void findCodedTriangleNumbers() {
        ArrayList<String> wordList = getWords("Euler/Problem42/words.txt");
        long maxCount = wordList.stream().mapToLong(CodedTriangleNumbers::getCharValueSum).max().getAsLong();
        ArrayList<Long> triangleNumbers = makeTriangleNumbers(maxCount);
        int count = 0;
        for (String word : wordList) {
            if (triangleNumbers.contains(getCharValueSum(word))) { count++; }
        }
        System.out.println("Number of triangle words:\t" + count);
    }

    public static long getCharValueSum(String str) {
        return str.replaceAll("[\\W\\d]", "").toUpperCase().chars().map(x -> x - 64).sum();
    }

    public static ArrayList<String> getWords(String path) {
        ArrayList<String> wordList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {

            stream.map(x -> x.split(",")).flatMap(Arrays::stream).map(x -> x.replaceAll("\"", "")).forEach(wordList::add);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }

    public static ArrayList<Long> makeTriangleNumbers(long upTo) {
        ArrayList<Long> list = new ArrayList<>();

        for (long i = 1; i <= upTo; i++) {
            list.add(returnTriangleNumber(i));
        }
        return list;
    }

    public static long returnTriangleNumber(long indexOf) {
        return (indexOf*(indexOf+1))/2;
    }
}
