/**
 * This is an example that creates a single thread by subclassing Thread.
 */

public class SimpleThread {

    private static class MySimpleThread extends Thread {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello from thread: " + threadName);
        }
    }

    public static void main(String[] args) {
        System.out.println("main thread: " + Thread.currentThread().getName());
        System.out.println("Starting 1 thread");
        Thread simpleThread = new MySimpleThread();
        simpleThread.start();
        System.out.println("Started thread");
    }
}
