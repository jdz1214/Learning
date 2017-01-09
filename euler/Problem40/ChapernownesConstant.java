package Problem40;

/**
 * Created by Duncan on 1/9/2017.
 */
public class ChapernownesConstant {

    public static void main(String[] args) {
        chapConst();
    }

    public static void chapConst() {
        String numStr = buildString();

        long product = 1L;
        for (int i = 1; i <= 1000000; i*=10) {
            product *= Character.getNumericValue(numStr.charAt(i-1));
        }
        System.out.println("Product:\t" + product);
    }

    public static String buildString() {
        String str = "";

        for (int i = 1; str.length() <= 1000000; i++) {
            str = str + String.valueOf(i);
        }

        return str;
    }


}
