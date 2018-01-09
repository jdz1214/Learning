package Problem62;

import Utils.NewUtes;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by jdz on 1/8/18.
 */
public class cubicPermutations {

    public static void main(String[] args) {
        int low = 1524;
        int high = 3000;
        int numberOfCubics = 5;

        findCubicPermutations(numberOfCubics, low, high);
    }

    public static void findCubicPermutations(int numberOfCubics, int low, int high) {
        if (numberOfCubics <= 1) {
            System.out.println("Invalid entry.");
        }
        else {
            ArrayList<String> cubeList = cubeList(low, high);
            //Iterate through cubeList
            int permCount = 0;
            for (String x : cubeList) {
                double ind = cubeList.indexOf(x);
                double percentage = ind / cubeList.size();

                System.out.printf("%.2f", percentage);
                System.out.println("% Complete. Checking index " + (int) ind + "(" + x + ")");
                permCount = 0;
                ArrayList<String> lengthList = new ArrayList<>();
                for (String cube : cubeList) {
                    if (x.length() == cube.length()) {
                        lengthList.add(cube);
                    }
                }
                Set<String> perms = NewUtes.generatePermutations(x);
                //Iterate through perms to identify cubes
                for (String p : perms) {
                    for (String checkCube : lengthList) {
                        if (p.equals(checkCube)) {
                            permCount++;
                            break;
                        }
                    }
                }
                if (permCount == numberOfCubics) {
                    System.out.println("Found " + x + " with " + numberOfCubics + " cubics.");
                    break;
                }
            }
            if (permCount != numberOfCubics) {
                System.out.println("Reached " + high + " to no avail.");
            }
        }
    }

    private static ArrayList<String> cubeList(int low, int high) {
        Long lower = (long) low;
        Long upper = (long) high;

        ArrayList<String> list = new ArrayList<>();

        for (long x = lower; x <= upper; x++) {
            Long Cube = x*x*x;
            list.add(Cube.toString());
        }

        return list;
    }
}
