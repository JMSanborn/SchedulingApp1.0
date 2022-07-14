package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.robot.Robot;

import java.util.*;
import java.net.URL;

public class mainController implements Initializable {


    public Button makeCoffee;
    public TextField coffeeAmount;
    public TextArea coffeeCup;
    static Timer timer;
    static double interval;
    public ProgressBar progressBar;
    public TextField progressText;
    private TextArea textAreaUI;
    public static TextArea staticTxtArea;
    double progress;
    double percent;


    public void OnMakeCoffee(ActionEvent actionEvent) throws Exception {
        if (coffeeAmount.getText().isEmpty()) {
            System.out.println("Incomplete Input / Input Null");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Please verify all field have been completed.");
            alert.showAndWait();
        } else {
            timer = new Timer();
            String text = coffeeAmount.getText();
            interval = Double.parseDouble(text);
            percent = (1/interval);
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    progressText.setText(setInterval() + (" cups of coffee left!"));
                    increaseProgress();
                    Platform.runLater(() -> {
                        keyStroke();
                    });
                }
            }, 0, 60*1000);
        }
    }

    private static final Double setInterval () {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }

    public void increaseProgress() {
        progress = progress + percent;
        progressBar.setProgress(progress);
    }

    public void keyStroke(){
        coffeeCup.requestFocus();
        Robot robot = new Robot();
            robot.keyPress(KeyCode.D);
            robot.keyRelease(KeyCode.D);
            System.out.println("rip");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progressBar.setStyle("-fx-accent: #DEB887;");
        staticTxtArea = coffeeCup;
    }

}
