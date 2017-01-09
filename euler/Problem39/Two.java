package Problem39;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Duncan on 1/9/2017.
 */
public class Two {

    public static void main(String[] args) {
        comboMaker(120);
    }

    public static void comboMaker(int perimeter) {
        Set<Set<Integer>> hypoSet = new HashSet<>();
        for (int i = 1; i < perimeter; i++) {
            for (int j = 1; j < perimeter; j++) {
                double a = Math.pow(i,2);
                double b = Math.pow(j,2);
                double hypoSquared = a + b;
                double hypo = Math.sqrt(hypoSquared);
                //System.out.println("a:\t" + i + "\tb:\t" + j + "\thypo\t" + hypo);
                if (hypo % 1.0 == 0.0) {
                    //System.out.println("Even hypotenuse:\t" + hypo);
                    if (hypo + i + j == perimeter) {
                        System.out.println("Integral right triangle: a:\t" + i + "\tb:\t" + j + "\thypo\t" + hypo);
                        Set<Integer> set = new HashSet<>();
                        set.add(i);
                        set.add(j);
                        hypoSet.add(set);
                    }
                }
            }
        }
        System.out.println("Integral set size:\t" + hypoSet.size());
        hypoSet.forEach(System.out::println);

    }
}
