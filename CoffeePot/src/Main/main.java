package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class main extends Application {

    /** Begins Application */

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/main.fxml"));
        primaryStage.setTitle("CoffeePot");
        primaryStage.setScene(new Scene(root, 500, 525));
        primaryStage.show();
    }

    /** Main function call */
    public static void main(String[] args) {
        launch(args);
    }

}
