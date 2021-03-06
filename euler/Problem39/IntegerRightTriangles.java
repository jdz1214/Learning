package Problem39;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Duncan on 1/8/2017.
 */
public class IntegerRightTriangles {

    public static void main(String[] args) {
        maximizeP();
    }

    public static void maximizeP() {
        int maxCount = 0;
        int maxPerimeter = 0;
        for (int i = 12; i <= 1000; i++) {
            Set<Set<Integer>> hypoSet = comboMaker(i);
            if (hypoSet.size() > maxCount) { maxPerimeter = i; maxCount = hypoSet.size(); }
        }
        System.out.println("Maximized perimeter with most integral right triangles:\t" + maxPerimeter + "\tnumber of triangles:\t" + maxCount);
    }

    public static Set<Set<Integer>> comboMaker(int perimeter) {
        Set<Set<Integer>> hypoSet = new HashSet<>();
        for (int i = 1; i < perimeter; i++) {
            for (int j = 1; j < perimeter; j++) {
                double a = Math.pow(i,2);
                double b = Math.pow(j,2);
                double hypoSquared = a + b;
                double hypo = Math.sqrt(hypoSquared);
                if (hypo % 1.0 == 0.0) {
                    if (hypo + i + j == perimeter) {
                        //System.out.println("Integral right triangle: a:\t" + i + "\tb:\t" + j + "\thypo\t" + hypo);
                        Set<Integer> set = new HashSet<>();
                        set.add(i);
                        set.add(j);
                        hypoSet.add(set);
                    }
                }
            }
        }
        return hypoSet;
    }
}