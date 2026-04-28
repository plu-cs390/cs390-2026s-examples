import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TrapezoidIntegrationExec1 {

    private static double a = 0.0, b = Math.PI;  // Integration limits
    private static int n = 1048576 * 2;              // Number of subdivisions (2^21)
    private static double h = (b - a) / n;       // Width of each subdivision

    private static double f( double x ) {
        return Math.sin(x);
    }

    public static void integrate()  {
        double result = ( f(a) + f(b) ) / 2.0;  // Initial value

        int chunkSize = n / 4;

        ExecutorService myService = Executors.newFixedThreadPool(4);

        Future<Double> f1 = myService.submit( new IntegrationTask(0, chunkSize) );
        Future<Double> f2 = myService.submit( new IntegrationTask(chunkSize, 2 * chunkSize) );
        Future<Double> f3 = myService.submit( new IntegrationTask(2 * chunkSize, 3 * chunkSize)  );
        Future<Double> f4 = myService.submit( new IntegrationTask(3 * chunkSize, n) );

        myService.shutdown();

        try {
            result += f1.get();
            result += f2.get();
            result += f3.get();
            result += f4.get();
            result *= h;
        } catch( InterruptedException | ExecutionException ex ) {
            ex.printStackTrace();
            System.exit(1);
        }

        System.out.printf("Trapezoids: %d, result = %f\n", n, result);
    }

    private static class IntegrationTask implements Callable<Double> {
        private int start, end;               // Range of segments for this task

        public IntegrationTask( int start, int end ) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Double call() {
            double sum = 0.0;
            for( int i = start; i < end; i++ ) {
                sum += f(a + i * h);
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        integrate();
        long end = System.currentTimeMillis();
        System.out.printf("Time = %.4f s\n", (end - start) / 1000.0f);
    }

}
