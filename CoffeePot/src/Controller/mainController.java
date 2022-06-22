package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
mport java.util.Timer;
import java.util.TimerTask;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class mainController implements Initializable {

    public Button makeCoffee;
    public TextField coffeeAmount;
    public TextArea coffeeCup;
    static Timer timer;
    static int interval;
    private TextArea textAreaUI;
    public static TextArea staticTxtArea;

    public void OnMakeCoffee(ActionEvent actionEvent) throws Exception {
        if (coffeeAmount.getText().isEmpty()) {

        System.out.println("Incomplete Input / Input Null");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText("Please verify all field have been completed.");
        alert.showAndWait();
    }
        else {
            System.err.println("@@@@ERROR: This is error");
            System.out.println("####OUTPUT : THIS IS ERROR");
        }
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        staticTxtArea = coffeeCup;
    }
}
