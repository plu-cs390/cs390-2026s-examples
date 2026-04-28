import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
 * This example demonstrates how the event dispatch thread can become
 * "blocked" causing the UI to become unresponsive.  Events are still
 * queued and executed later, but the UI does not update.  In this example,
 * when we click the "Do Work" button, we start computing some prime
 * numbers which takes some time.  In the meantime, we can try to click
 * the "Click Me" button, but we see no output, and the UI is unresponsive.
 * After the computation of the primes is finished, we see all of our
 * clicks of the "Click Me" button printed to the screen.
 */

public class GuiWork extends Application {

    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread: " + threadName + " finished creating frame.");
        System.out.println("Is event thread? " + Platform.isFxApplicationThread());

        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Do Work in Event Thread");

        HBox buttonPanel = new HBox();

        Button clickMeButton = new Button("Click Me");
        clickMeButton.setOnAction((e) -> clickMeButtonAction());

        Button doWorkButton = new Button("Do Work");
        doWorkButton.setOnAction((e) -> doWorkButtonAction());

        buttonPanel.getChildren().addAll(clickMeButton, doWorkButton);
        primaryStage.setScene( new Scene(buttonPanel, 400, 100));
        primaryStage.show();
    }

    private void clickMeButtonAction() {
        // This will be executed on the event dispatch thread
        String threadName = Thread.currentThread().getName();

        System.out.println("Thread: " + threadName + " in clickMeButtonAction");
        System.out.println("Is event thread? " + Platform.isFxApplicationThread());
    }

    private void doWorkButtonAction() {
        String threadName = Thread.currentThread().getName();

        // This will also be executed on the event dispatch thread
        System.out.println("Thread: " + threadName + " in doWorkButtonAction");
        System.out.println("Is event thread? " + Platform.isFxApplicationThread());

        // Let's do a bunch of work...
        System.out.println("Computing primes...");
        findSomePrimes();
        System.out.println("Done.");
    }

    private void findSomePrimes() {
        for( long p = 10000000000000000L; p < 10000000000010000L; p++ ) {
            if( isPrime(p) ) {
                System.out.println("Found prime: " + p);
            }
        }
    }

    private boolean isPrime( long num ) {
        long end = (long)Math.ceil( Math.sqrt((double)num) );
        for( long i = 2; i <= end; i++ ) {
            if( num % i == 0 ) return false;
        }
        return true;
    }
}
