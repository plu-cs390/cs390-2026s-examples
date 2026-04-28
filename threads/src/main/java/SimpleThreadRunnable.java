/**
 * This example creates a single thread using the Runnable interface.
 * It also uses join to cause the main thread to wait for the other
 * thread to finish.
 */

public class SimpleThreadRunnable {

    private static class MySimpleThreadRunnable implements Runnable {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello from thread: " + threadName);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main thread: " + Thread.currentThread().getName());
        System.out.println("Starting 1 thread");
        Runnable simpleRunnable = new MySimpleThreadRunnable();
        Thread simpleThread = new Thread(simpleRunnable);
        simpleThread.start();
        System.out.println("Waiting for thread to finish...");
        simpleThread.join();
        System.out.println("Thread is complete.");
    }
}
