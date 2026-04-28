public class SleepyThread {

    private static class PoeticThreadRunnable implements Runnable {

        @Override
        public void run() {
            String[] poem = {
                    "I wandered lonely as a cloud,",
                    "That floats on high o'er vales and hills,",
                    "When all at once I saw a crowd,",
                    "A host of golden daffodils;",
                    "Beside the lake, beneath the trees,",
                    "Fluttering and dancing in the breeze."
            };

            for( String line : poem ) {
                System.out.println(line);

                try {
                    Thread.sleep(1000);
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new PoeticThreadRunnable());
        t.start();
        t.join();
        System.out.println("Done.");
    }
}
