/**
 * This example demonstrates how a wrong result can occur due to
 * race conditions.
 */
public class BadCounter {

    private static class BadCounterRunnable implements Runnable {

        private int count;

        public BadCounterRunnable() {
            count = 0;
        }

        public int getCount() { return count; }

        @Override
        public void run() {
            for( int i = 0; i < 100000; i++ ) {
                count++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BadCounterRunnable counter = new BadCounterRunnable();
        Thread t1 = new Thread(counter);
        Thread t2 = new Thread(counter);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count = " + counter.getCount());
    }
}
