package Problem45;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jdz on 1/11/17.
 */
public class TriPentHex {

    public static void main(String[] args) {
        trifecta(145, 100000);

    }

    public static void trifecta(long lowerLimit, long upperLimit) {
        System.out.println("Running with upperlimit of " + upperLimit);
        ArrayList<Long> triangles = generateTriangles(lowerLimit, upperLimit);
        ArrayList<Long> pentagons = generatePentagons(lowerLimit, upperLimit);
        ArrayList<Long> hexagons = generateHexagons(lowerLimit, upperLimit);

        boolean found = false;
        for (Long t : triangles) {
            if (Collections.max(pentagons) < t || Collections.max(hexagons) < t) {
                trifecta(lowerLimit, upperLimit * 2);
            } else if (pentagons.contains(t) && hexagons.contains(t)) {
                found = true;
                System.out.println("Next TriPentHex Number:\t" + t);
                break;
            }
        }
        if (!found) {
                trifecta(lowerLimit, upperLimit * 2);
        }
    }

    public static ArrayList<Long> generateTriangles(long lowerLimit, long upperLimit) {
        ArrayList<Long> list = new ArrayList<>();
        for (long i = lowerLimit; i <= upperLimit; i++) {
            long tri = (i * (i + 1)) / 2;
            list.add(tri);
        }
        return list;
    }

    public static ArrayList<Long> generatePentagons(long lowerLimit, long upperLimit) {
        ArrayList<Long> list = new ArrayList<>();
        for (long i = lowerLimit; i <= upperLimit; i++) {
            long pent = (i * (3 * i - 1)) / 2;
            list.add(pent);
        }
        return list;
    }

    public static ArrayList<Long> generateHexagons(long lowerLimit, long upperLimit) {
        ArrayList<Long> list = new ArrayList<>();
        for (long i = lowerLimit; i <= upperLimit; i++) {
            long hex = i * (2 * i - 1);
            list.add(hex);
        }
        return list;
    }
}
