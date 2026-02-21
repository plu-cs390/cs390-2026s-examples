import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class MathQuestion extends Application {

    private int term1, term2;

    private Random rand;

    private Label problemLabel;

    private TextField answerField;

    private Label responseLabel;

    public MathQuestion() {
        rand = new Random();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox mainPanel = new VBox();
        mainPanel.setAlignment(Pos.CENTER_LEFT);

        HBox questionPanel = new HBox();
        questionPanel.setAlignment(Pos.CENTER_LEFT);

        problemLabel = new Label();
        answerField = new TextField();
        answerField.setPrefWidth(90);

        answerField.setOnAction( this::checkAnswer );

        VBox.setMargin(questionPanel, new Insets(10));
        questionPanel.getChildren().addAll(problemLabel, answerField);

        responseLabel = new Label("Type the answer and press enter");
        VBox.setMargin(responseLabel, new Insets(10));

        generateQuestion();

        mainPanel.getChildren().addAll(questionPanel, responseLabel);

        primaryStage.setScene(new Scene(mainPanel, 400, 100));
        primaryStage.setTitle("Math Question");
        primaryStage.show();
    }

    private void checkAnswer(ActionEvent actionEvent) {
        String text = answerField.getText();
        int answer = Integer.parseInt(text);
        if( answer == term1 + term2 ) {
            responseLabel.setText("Correct!!  Here's another question.");
            generateQuestion();
        }
        else {
            responseLabel.setText("Incorrect, please try again.");
        }
    }

    private void showAnswer() {
        int answer = term1 + term2;
        responseLabel.setText("The correct answer is: " + answer);
    }

    private void generateQuestion() {
        term1 = rand.nextInt(1, 13);
        term2 = rand.nextInt(1, 13);
        problemLabel.setText( term1 + " + " + term2 + " = ");
        answerField.setText("");
    }

    public static void main(String[] args) {
        launch();
    }
}
