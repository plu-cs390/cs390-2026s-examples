/**
 * This example demonstrates how we could fix the BadCounter example
 * by not sharing the counter.
 */
public class BetterCounter {

    private static class BetterCounterRunnable implements Runnable {

        private int count;

        public BetterCounterRunnable() {
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
        BetterCounterRunnable counter1 = new BetterCounterRunnable();
        BetterCounterRunnable counter2 = new BetterCounterRunnable();

        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count = " + (counter1.getCount() + counter2.getCount()));
    }
}
