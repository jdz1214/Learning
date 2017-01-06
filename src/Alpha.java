import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created by Duncan on 12/27/2016.
 */
public class Alpha {
    static String fileName = "p022_names.txt";
    static String path = "resources\\";
    public static void main(String[] args) throws IOException {
        Alpha a = new Alpha();

    }



    public void nonAbundantSums() {
        //from 1 to 28123
        ArrayList<Long> abundants = LongStream.range(1, 28124).boxed().filter(this::isAbundant).collect(Collectors.toCollection(ArrayList::new));
        Set<Long> abundantSums = new HashSet();
        for(int i = 0; i < abundants.size() / 2; i++) {
            for (long x : abundants) {
                if((abundants.get(i) + x) < 28124) { abundantSums.add(abundants.get(i) + x); }
            }
        }
        abundantSums.stream().forEach(System.out::println);
        ArrayList<Long> nonAbundants = LongStream.range(1, 28124).boxed().collect(Collectors.toCollection(ArrayList::new));
        nonAbundants.removeAll(abundantSums);
        nonAbundants.stream().forEach(System.out::println);
        System.out.println("Sum of nonAbundants:\t" + nonAbundants.stream().mapToLong(Long::valueOf).sum());
    }

    public Boolean isAbundant(long n) {
        return sumDivisors(n) > n;
    }

    public Long sumDivisors(Long num) {
        long sum = 0L;
        for (long i = 1L; i < num; i++) {
            if (num % i == 0) { sum += i; }
        }
        return sum;
    }

    public void totalNameScores() throws IOException {
        String[] names = fileHandler(path, fileName);
        ArrayList<String> list = new ArrayList<>(Arrays.stream(names).collect(Collectors.toList()));
        Collections.sort(list);
        Long totalNameScores = 0L;
        for(int i = 0; i < list.size(); i++) {
            totalNameScores += nameScore(i, list.get(i));
        }
        System.out.println("Sum of all name scores:\t" + totalNameScores);
    }

    public int nameScore (int position, String name) {
        int nameScore = name.chars().map(c -> getLetterNumber((char)c)).sum();
        return nameScore * (position + 1);
    }

    public String[] fileHandler(String path, String fileName) throws IOException {
        FileReader fr = new FileReader(path + fileName);
        BufferedReader br = new BufferedReader(fr);
        String text = br.readLine();
        return text.trim().replace("\"", "").split(",");
    }

    public int getLetterNumber(char c) {
        Map<Character, Integer> map = new HashMap();
        map.put('a', 1);
        map.put('b', 2);
        map.put('c', 3);
        map.put('d', 4);
        map.put('e', 5);
        map.put('f', 6);
        map.put('g', 7);
        map.put('h', 8);
        map.put('i', 9);
        map.put('j', 10);
        map.put('k', 11);
        map.put('l', 12);
        map.put('m', 13);
        map.put('n', 14);
        map.put('o', 15);
        map.put('p', 16);
        map.put('q', 17);
        map.put('r', 18);
        map.put('s', 19);
        map.put('t', 20);
        map.put('u', 21);
        map.put('v', 22);
        map.put('w', 23);
        map.put('x', 24);
        map.put('y', 25);
        map.put('z', 26);
        return map.get(Character.toLowerCase(c));
    }
}
