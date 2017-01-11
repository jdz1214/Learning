package Problem44;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Duncan on 1/9/2017.
 */
public class PentagonNumbers {

    public static void main(String[] args) {
        execute();
    }

    public static void execute() {
        ArrayList<Long> pents = generatePentagonals(2500);
        Long min = 0L;
        for (Long x : pents) {
            for (Long y : pents) {
                if (!Objects.equals(y, x) && sumDiffArePentagonal(pents, x, y)) { Long diff = Math.abs(x - y); if (min == 0 || diff < min) { min = diff; } }
            }
        }

        System.out.println("Minimum difference:\t" + min);
    }

    public static boolean sumDiffArePentagonal(ArrayList<Long> list, long pentagonalJ, long pentagonalK) {
        return list.contains(pentagonalJ + pentagonalK) && list.contains(Math.abs(pentagonalJ - pentagonalK)) && pentagonalJ != pentagonalK;
    }

    public static ArrayList<Long> generatePentagonals(long maxIndex) {
        ArrayList<Long> list = new ArrayList<>();
        for (long i = 1; i <= maxIndex; i++) {
            list.add((i*(3 * i - 1)) / 2);
        }
        return list;
    }

}
