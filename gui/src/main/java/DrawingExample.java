import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DrawingExample extends Application {

    private double radius;

    private Color circleColor;

    private GraphicsContext g;

    public DrawingExample() {
        radius = 15.0;
        circleColor = Color.BLUE;
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane mainPane = new StackPane();

        Canvas canvas = new Canvas(500,500);
        g = canvas.getGraphicsContext2D();
        mainPane.getChildren().add(canvas);

        canvas.setOnMouseDragged( e -> {
            drawCircle( new Point2D(e.getX(), e.getY()) );
        });

        primaryStage.setScene( new Scene(mainPane, 500,500));
        primaryStage.setTitle("Drawing Example");
        primaryStage.show();
    }

    private void drawCircle(Point2D pt) {
        g.setFill(circleColor);
        g.fillOval( pt.getX() - radius, pt.getY() - radius, 2*radius, 2*radius);
    }

    public static void main(String[] args) {
        launch();
    }
}
