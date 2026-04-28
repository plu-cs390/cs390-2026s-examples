/**
 * This example demonstrates how threads interleave their operations in a non-deterministic
 * way.
 */

public class CountingThreads {

    private static class CounterRunnable implements Runnable {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            for( int i = 0; i < 100; i++ ) {
                System.out.println(threadName + ": " + i);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new CounterRunnable()).start();
        new Thread(new CounterRunnable()).start();
    }
}
