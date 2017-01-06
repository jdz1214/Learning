import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Duncan on 12/21/2016.
 */
public class While {

    public static void main(String[] args) {
        While w = new While();
        w.loops();
    }

    public void loops() {


        ExecutorService executor = Executors.newFixedThreadPool(2);
        ArrayList<Callable<Integer>> list = new ArrayList<>();

        Callable<Integer> t1 = () -> {
            int divisors = 1;
            while (divisors < 500) {
                if (Thread.currentThread().isInterrupted()) {
                    throw new RuntimeException();
                }
                divisors++;
                System.out.println("divisors:\t" + divisors);
            }
            return divisors;
        };

        Callable<Integer> t2 = () -> {
            int divisors = 2;
            while (divisors < 500) {
                if (Thread.currentThread().isInterrupted()) {
                    throw new RuntimeException();
                }
                divisors++;
                System.out.println("divisors:\t" + divisors);
                //if(divisors < 500) { break; }
            }
            return divisors;
        };


        list.add(t1);
        list.add(t2);


        int result = 0;
        try {
            result = executor.invokeAny(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdownNow();
        System.out.println("Result:\t" + result);
    }
}
