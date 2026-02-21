import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PushCounter extends Application {

    private int pushCount = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(makeScene());
        primaryStage.setTitle("Push Counter");
        primaryStage.show();
    }

    private Scene makeScene() {
        HBox mainPanel = new HBox();

        mainPanel.setAlignment(Pos.CENTER_LEFT);
        Button pushMeButton = new Button("Push Me");
        Label countLabel = new Label("Pushes: 0");

        pushMeButton.setOnAction( e -> {
            pushCount++;
            countLabel.setText("Pushes: " + pushCount);
        });

        HBox.setMargin(pushMeButton, new Insets(10));

        mainPanel.getChildren().addAll(pushMeButton, countLabel);

        return new Scene(mainPanel, 300, 75);
    }

    public static void main(String[] args) {
        launch();
    }
}
