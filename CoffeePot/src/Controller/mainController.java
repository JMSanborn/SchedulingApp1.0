package Controller;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.robot.Robot;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.net.URL;

public class mainController implements Initializable {


    public Button makeCoffee;
    public TextField coffeeAmount;
    public TextArea coffeeCup;
    static Timer timer;
    static int interval;
    public ProgressBar progressBar;
    private TextArea textAreaUI;
    public static TextArea staticTxtArea;
    double progress;




    public void OnMakeCoffee(ActionEvent actionEvent) throws Exception {
        if (coffeeAmount.getText().isEmpty()) {
            System.out.println("Incomplete Input / Input Null");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Please verify all field have been completed.");
            alert.showAndWait();
        } else {
            progressBar.setProgress(1);
            timer = new Timer();
            String text = coffeeAmount.getText();
            interval = Integer.parseInt(text);
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    System.out.println(setInterval() + (" Minutes of fuel left!"));
                    increaseProgress();
                    Platform.runLater(() -> {
                        Robot robot = new Robot();
                        robot.mouseMove(10, 20);
                        //robot.mousePress(MouseButton.PRIMARY);
                        //robot.mouseRelease(MouseButton.PRIMARY);
                        robot.mouseMove(-20, -10);
                        //robot.mousePress(MouseButton.PRIMARY);
                        //robot.mouseRelease(MouseButton.PRIMARY);
                    });
                }
            }, 0, 60*10);
        }
    }

    private static final int setInterval () {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }

    public void increaseProgress(){
        progress += 0.1;
        progressBar.setProgress(progress);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progressBar.setStyle("-fx-accent: #ADFF2F;");
        progressBar.setProgress(1);
        staticTxtArea = coffeeCup;
    }

}
