package Problem26;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created by Duncan on 1/1/2017.
 */
public class RecurringCycles {

    public static void main(String[] args) throws IOException {
        longestRecurringCycle();
    }

    public static void longestRecurringCycle() {
        Map<Double, String> map = LongStream.range(1, 1000)
                .asDoubleStream()
                .boxed()
                .collect(Collectors.toMap(y -> y, x -> {
                    BigDecimal a = new BigDecimal(1);
                    BigDecimal b = new BigDecimal(x);
                    return a.divide(b, 10000, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
                }));
        map.entrySet().stream().filter(e -> e.getValue().length() < 20).forEach(map::remove);
        Map<Double, Integer> mapWithCount = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, x -> findLongestPattern(x.getValue()).length()));


        Map.Entry<Double, Integer> maxEntry = null;

        for (Map.Entry<Double, Integer> entry : mapWithCount.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }

        System.out.println("Max of map:\t" + maxEntry.getKey() + "\tMax repeating pattern length:\t" + maxEntry.getValue());
    }

    public static String findLongestPattern (String str) {
        Pattern pattern = Pattern.compile("(.+?)\\1");
        Matcher matcher = pattern.matcher(str);
        matcher.find();
        if(matcher.find()) { return matcher.group(matcher.groupCount()); }
        return "No match found.";
    }

    public static void toTxt(ArrayList<String> list, String filenameWithoutTxt) {
        try {
            FileWriter writer = new FileWriter("resources\\" + filenameWithoutTxt + ".txt");
            for (String str : list) { System.out.println("str:\t" + str); writer.write(str + "\n");}
            writer.close();
        } catch (IOException e) { e.printStackTrace();}
        System.out.println("Wrote " + filenameWithoutTxt + ".txt");
    }

    public static List<String> fromTxt(String filePathAndName) {
        ArrayList<String> list = new ArrayList<>();
        try {
            list.addAll(Files.readAllLines(Paths.get(filePathAndName)));
        } catch (IOException e) { e.printStackTrace();}
        return list;
    }
}
