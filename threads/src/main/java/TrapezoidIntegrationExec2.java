import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TrapezoidIntegrationExec2 {

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

        List<IntegrationTask> tasks = new ArrayList<>();
        tasks.add( new IntegrationTask(0, chunkSize) );
        tasks.add( new IntegrationTask(chunkSize, 2 * chunkSize) );
        tasks.add( new IntegrationTask(2 * chunkSize, 3 * chunkSize)  );
        tasks.add( new IntegrationTask(3 * chunkSize, n) );

        try {
            List<Future<Double>> results = myService.invokeAll(tasks);
            myService.shutdown();

            for( Future<Double> f : results ) {
                result += f.get();
            }
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
