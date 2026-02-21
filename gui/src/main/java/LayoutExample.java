import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LayoutExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox mainContainer = new VBox();

        HBox topControls = new HBox();
        VBox.setMargin( topControls, new Insets(10.0) );
        topControls.setAlignment( Pos.BOTTOM_LEFT );

        Button btnRefresh = new Button("Refresh");

        HBox topRightControls = new HBox();
        HBox.setHgrow(topRightControls, Priority.ALWAYS );
        topRightControls.setAlignment( Pos.BOTTOM_RIGHT );
        Hyperlink signOutLink = new Hyperlink("Sign Out");
        topRightControls.getChildren().add( signOutLink );

        topControls.getChildren().addAll( btnRefresh, topRightControls );

        StackPane centerPane = new StackPane();
        centerPane.setBackground(new Background(
                new BackgroundFill(Color.CYAN, new CornerRadii(0), new Insets(0)))
        );
        Label label = new Label("Example Layout");
        VBox.setMargin( centerPane, new Insets(0.0, 10.0, 10.0, 10.0) );
        VBox.setVgrow( centerPane, Priority.ALWAYS );
        centerPane.getChildren().add(label);

        Separator sep = new Separator();

        HBox bottomControls = new HBox();
        bottomControls.setAlignment(Pos.BOTTOM_RIGHT );
        VBox.setMargin( bottomControls, new Insets(10.0) );

        Button btnClose = new Button("Close");

        bottomControls.getChildren().add( btnClose );

        mainContainer.getChildren().addAll(
                topControls,
                centerPane,
                sep,
                bottomControls
        );

        primaryStage.setScene( new Scene(mainContainer, 800, 600) );
        primaryStage.setTitle("Example Layout");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
