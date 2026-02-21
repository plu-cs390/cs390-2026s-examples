import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StageExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new Pane(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello GUI");
        primaryStage.show();
    }

    public static void main( String[] args ) {
        launch();
    }

}
