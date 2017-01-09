package Problem39;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Duncan on 1/8/2017.
 */
public class IntegerRightTriangles {
    private static Set<Set<Integer>> integralTriangleSet = new HashSet<>();

    public static void main(String[] args) {
        maximizeP();
        System.out.println("set size:\t" + integralTriangleSet.size());
        //integralTriangleSet.forEach(RightTriangle::printMe);

    }

    public static void maximizeP() {
        generatePerimeterCombos(1000, 1, 1);
        System.out.println("number of integral right triangles generated:\t" + integralTriangleSet.size());
        Map<Integer, RightTriangle> map = integralTriangleSet.stream().map(x -> {
            Iterator<Integer> it = x.iterator();
            ArrayList<Integer> list = new ArrayList<>();
            while (it.hasNext()) {
                list.add(it.next());
            }
            return new RightTriangle(list.get(0),list.get(1));
            })
            .filter(tri -> tri.isIntegral)
            .collect(Collectors.toMap(z -> (int) z.getPerimeter(), y -> y));
        Map.Entry entry = null;
        long maxCount = 0;
        for (Map.Entry<Integer, RightTriangle> e : map.entrySet()) {
            long thisCount = map.entrySet().stream().filter(x -> Objects.equals(x.getKey(), e.getKey())).count();
            if (entry == null || thisCount > maxCount) {
                entry = e;
                maxCount = thisCount;
            }
        }
        if ((entry != null ? entry.getKey() : null) == null) {
            System.out.println("Nothing found.");
        } else {
            System.out.println("Perimeter value with max integral right triangles:\t" + entry.getKey());
        }
    }

    public static void generatePerimeterCombos(int perimeterUpperLimit, int a, int b) {
        Double hypo = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        double perimeter = a + b + hypo;
        if (perimeter <= perimeterUpperLimit) {
            System.out.println("a:\t" + a + "\tb:\t" + b + "\thypo:\t" + hypo + "\tperimeter:\t" + (a + b + hypo));
            if (hypo % 1.0 == 0.0) {
                HashSet<Integer> integralTriSides = new HashSet<>();
                integralTriSides.add(a);
                integralTriSides.add(b);
                integralTriangleSet.add(integralTriSides);
                System.out.println("Triangle added.");
            }

                if (a < 499) { generatePerimeterCombos(perimeterUpperLimit, a + 1, b); }
                if (b < 499) { generatePerimeterCombos(perimeterUpperLimit, a, b + 1); }
        }
    }

    public static boolean isIntegralRightTriangle(RightTriangle rightTriangle) {
        double aSquared = Math.pow(rightTriangle.getA(), 2);
        double bSquared = Math.pow(rightTriangle.getB(), 2);
        double hypoSquared = Math.pow(rightTriangle.getHypotenuse(), 2);

        if (aSquared % 1.0 == 0.0 && bSquared % 1.0 == 0.0 && hypoSquared % 1.0 == 0.0) {
            return aSquared + bSquared == hypoSquared;
        }
        return false;
    }

    static class RightTriangle {
        private double a;
        private double b;
        private double hypotenuse;
        private double perimeter;
        private boolean isIntegral;

        public RightTriangle(int sideA, int sideB) {
            a = (double) sideA;
            b = (double) sideB;
            hypotenuse = deriveHypotenuse(sideA, sideB);
            perimeter = a + b + hypotenuse;
            isIntegral = isIntegralRightTriangle(this);
        }

        private double deriveHypotenuse(int a, int b) {
            double aDbl = (double) a;
            double bDbl = (double) b;
            double hypoSquared = Math.pow(aDbl, 2) + Math.pow(bDbl, 2);
            return Math.sqrt(hypoSquared);
        }

        public void printMe() { System.out.println("a:\t" + this.getA() + "\tb:\t" + this.getB() + "\thypo:\t" + this.getHypotenuse() + "\tperimeter:\t" + this.getPerimeter() + "\tisIntegral:\t" + this.isIntegral); }
        public double getA() { return a; }
        public double getB() { return b; }
        public double getHypotenuse() { return hypotenuse; }
        public double getPerimeter() { return perimeter; }
    }


}