
public class InterruptExample1 {

    private static class InterruptMeRunnable implements Runnable {
        @Override
        public void run() {
            for( int i = 0; i < 100; i++ ) {
                System.out.println(i);
                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {
                    System.out.println("I was interrupted!");
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread( new InterruptMeRunnable());
        t.start();
        Thread.sleep(500);
        t.interrupt();
    }
}