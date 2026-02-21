import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloScene extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = makeAScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello GUI");
        primaryStage.show();
    }

    private Scene makeAScene() {
        Pane rootNode = new StackPane(new Text("JavaFX is awesome"));
        return new Scene(rootNode, 300, 300);
    }

    public static void main( String[] args ) {
        launch();
    }

}
