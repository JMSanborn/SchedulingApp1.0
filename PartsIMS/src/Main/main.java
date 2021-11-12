package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Main class for the JavaFX application "Robot Inventory Management System".
 * @author Jason M. Sanborn
 * Software 1 -C482
 * Instructor -Juan Ruiz
 * 1.Future Enhancement - Time Stamp - I would incorperate a tamp stamp method that would return a date and time anytime a part or product was created or modified.
 * This would then be utilized as a new scenne object.
 */

public class main extends Application {

    /** Begins Application */

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/View/main.fxml"));
            primaryStage.setTitle("Robot IMS");
            primaryStage.setScene(new Scene(root, 1100, 525));
            primaryStage.show();
    }

    /** Main function call */
    public static void main(String[] args) {
        launch(args);
    }
}
