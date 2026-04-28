
public class InterruptExample2 {

    private static class InterruptMeRunnable implements Runnable {
        @Override
        public void run() {
            for( int i = 0; i < 10000; i++ ) {
                System.out.println(i);
                if( Thread.interrupted() ) {
                    System.out.println("I was interrupted!");
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread( new InterruptMeRunnable());
        t.start();
        Thread.sleep(10);
        t.interrupt();
    }
}