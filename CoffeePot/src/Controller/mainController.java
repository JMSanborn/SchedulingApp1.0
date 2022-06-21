package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.OutputStream;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class mainController {

    public Button makeCoffee;
    public TextField coffeeAmount;
    public TextArea coffeeCup;
    static Timer timer;
    static int interval;

    public void OnMakeCoffee(ActionEvent actionEvent) throws Exception {
        if (coffeeAmount.getText().isEmpty()) {

        System.out.println("Incomplete Input / Input Null");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText("Please verify all field have been completed.");
        alert.showAndWait();
    }
        else{
        Scanner sc = new Scanner(System.in);
        if (coffeeAmount.getText().isEmpty()
        String secs = sc.nextLine();
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = Integer.parseInt(secs);
        System.out.println(secs);
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                System.out.println(setInterval());

            }
        }, delay, period);
    }

    private static final int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }
}
