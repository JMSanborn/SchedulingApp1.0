package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.util.*;
import java.net.URL;

public class mainController implements Initializable {


    public Button makeCoffee;
    public TextField coffeeAmount;
    public TextArea coffeeCup;
    static Timer timer;
    static int interval;
    public ProgressBar coffeeBar;
    private TextArea textAreaUI;
    public static TextArea staticTxtArea;

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
            interval = Integer.parseInt(text);
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    System.out.println(setInterval() + (". Coffee is good!"));
                    ProgressBar coffeeBar = new ProgressBar(0);
                    coffeeBar.setProgress(0.25);

                }
            }, 0, 60*1000);
        }
    }

    private static final int setInterval () {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        staticTxtArea = coffeeCup;
    }
}
