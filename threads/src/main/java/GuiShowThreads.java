import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
 * This example demonstrates the two threads that exist with every Java
 * program that includes a GUI.  We show the main (initial) thread, and
 * the JavaFX application thread (event dispatch), that the listeners execute on.
 *
 * We use Platform.isFxApplicationThread() to query whether or not
 * we're executing on the event dispatch thread, and we use
 * Thread.currentThread().getName() to get the name of the current thread.
 */

public class GuiShowThreads extends Application {

    public static void main(String[] args) {
        // Since we're in the main method, we should be executing on the
        // main thread.  This thread is the initial thread for all Java
        // applications.  It is NOT the same as the event dispatching thread.
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread: " + threadName + " before application launch.");
        System.out.println("Is event thread? " + Platform.isFxApplicationThread());

        launch();
    }

    private void buttonClicked() {
        // Callbacks are called on the event dispatching thread, so
        // we should see here that the thread name for this thread is not main
        String threadName = Thread.currentThread().getName();

        System.out.println("Thread: " + threadName + " in buttonClicked");
        System.out.println("Is event thread? " + Platform.isFxApplicationThread());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Show JavaFX threads");

        StackPane buttonPanel = new StackPane();

        Button clickMeButton = new Button("Click Me");
        clickMeButton.setOnAction((e) -> buttonClicked());

        buttonPanel.getChildren().add(clickMeButton);

        Scene scn = new Scene(buttonPanel, 400, 100 );

        primaryStage.setScene(scn);
        primaryStage.show();
    }
}
