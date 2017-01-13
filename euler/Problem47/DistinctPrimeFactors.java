package Problem47;

/**
 * Created by Duncan on 1/13/2017.
 */
public class DistinctPrimeFactors {

    public static void main(String[] args) {
        //hasDistinctPrimeFactors(644);
    }

//    public static boolean hasDistinctPrimeFactors(long n) {
//        long[] arr = MathUtils.getPrimeFactors(n);
//        boolean f = Arrays.stream(arr).reduce((x , y) -> x * y).ifPresent(z -> {
//            if (z < n) {
//                Arrays.stream(arr).filter(a -> z * a == n).findFirst().ifPresent(b -> {
//
//                    Arrays.stream(arr).forEach(s -> System.out.print(s + "\t"));
//                    System.out.print("\tUse factor " + b + " twice.");
//                    return true;
//                });
//            } else if (z == n) { Arrays.stream(arr).forEach(r -> System.out.print(r + "\t")); System.out.print("\t == " + n); }
//        });
//        return f;
//    }
}


//    public static boolean hasDistinctPrimeFactors(long n) {
//        long[] arr = MathUtils.getPrimeFactors(n);
//        Predicate<Long> d1 = d -> {
//            if (d < n) {
//                Arrays.stream(arr).filter(a -> d * a == n).findFirst().ifPresent(b -> {
//                    Arrays.stream(arr).forEach(s -> System.out.print(s + "\t"));
//                    System.out.print("\tUse factor " + b + " twice.");
//                });
//            }
//            return false;
//        };
//
//
//
//
//
//        Arrays.stream(arr).reduce((x , y) -> x * y).ifPresent(z -> {
//            if (z < n) {
//                return Arrays.stream(arr).filter(a -> z * a == n).allMatch(d1).findFirst();
//            } else if (z == n) { Arrays.stream(arr).forEach(r -> System.out.print(r + "\t")); System.out.print("\t == " + n); }
//        });
//    }