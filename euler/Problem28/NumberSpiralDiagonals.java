package Problem28;

/**
 * Created by Duncan on 1/4/2017.
 */
public class NumberSpiralDiagonals {
    public static void main(String[] args) {
        numberSpiralDiagonals();
    }

    public static void numberSpiralDiagonals() {
        int sumDiagonals = 1;
        int a = 1;
        for (int n = 3; n <= 1001; n += 2) {
            for (int i = 1; i <= 4; i++) {
                if (i == 4) {
                    a = a + (n - 1) * i;
                    sumDiagonals += a;
                } else {
                    sumDiagonals += a + (n - 1) * i;
                }
            }
        }

        System.out.println("sumDiagonals:\t" + sumDiagonals);
    }
}
