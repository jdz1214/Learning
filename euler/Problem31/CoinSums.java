package Problem31;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Duncan on 1/6/2017.
 */
public class CoinSums {
    public static void main(String[] args) {
        calculate2Pounds();
    }

    public static void calculate2Pounds() {
        Set<int[]> set = new HashSet<>();

        for (int num2£ = 0; num2£ <= 1; num2£++) {
            for (int num1£ = 0; num1£ <= 2; num1£++) {
                for (int num50p = 0; num50p <= 4; num50p++) {
                    for (int num20p = 0; num20p <= 10; num20p++) {
                        for (int num10p = 0; num10p <= 20; num10p++) {
                            for (int num5p = 0; num5p <= 40; num5p++) {
                                for (int num2p = 0; num2p <= 100; num2p++) {
                                    for (int num1p = 0; num1p <= 200; num1p++) {
                                        double pounds = getPounds(num1p, num2p, num5p, num10p, num20p, num50p, num1£, num2£);
                                        if (pounds > 2.00) { break; }
                                        if (pounds == 2.00) { set.add(new int[] {num1p, num2p, num5p, num10p, num20p, num50p, num1£, num2£}); break; }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int[] s : set) {
            Arrays.stream(s).forEach(x -> System.out.print(x + "\t"));
            System.out.println();
        }

        System.out.println("set size (number of combinations):\t" + set.size());
    }

    public static double getPounds(int num1p, int num2p, int num5p, int num10p, int num20p, int num50p, int num1£, int num2£) {
        BigDecimal oneP = BigDecimal.valueOf((double) num1p / 100);
        BigDecimal twoP = BigDecimal.valueOf((double) num2p / 50);
        BigDecimal fiveP = BigDecimal.valueOf((double) num5p / 20);
        BigDecimal tenP = BigDecimal.valueOf((double) num10p / 10);
        BigDecimal twentyP = BigDecimal.valueOf((double) num20p / 5);
        BigDecimal fiftyP = BigDecimal.valueOf((double) num50p / 2);
        BigDecimal sum = oneP.add(twoP.add(fiveP.add(tenP.add(twentyP.add(fiftyP.add(BigDecimal.valueOf(num1£).add(BigDecimal.valueOf(2*num2£))))))));



        return sum.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double getPounds(int[] moneyInputs) {
        int num1p = moneyInputs[0];
        int num2p = moneyInputs[1];
        int num5p = moneyInputs[2];
        int num10p = moneyInputs[3];
        int num20p = moneyInputs[4];
        int num50p = moneyInputs[5];
        int num1£ = moneyInputs[6];
        int num2£ = moneyInputs[7];

        BigDecimal oneP = BigDecimal.valueOf((double) num1p / 100);
        BigDecimal twoP = BigDecimal.valueOf((double) num2p / 50);
        BigDecimal fiveP = BigDecimal.valueOf((double) num5p / 20);
        BigDecimal tenP = BigDecimal.valueOf((double) num10p / 10);
        BigDecimal twentyP = BigDecimal.valueOf((double) num20p / 5);
        BigDecimal fiftyP = BigDecimal.valueOf((double) num50p / 2);
        BigDecimal sum = oneP.add(twoP.add(fiveP.add(tenP.add(twentyP.add(fiftyP.add(BigDecimal.valueOf(num1£).add(BigDecimal.valueOf(2*num2£))))))));

        return sum.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
