package Main;

import Database.jdbc;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/** Main class for the JavaFX application "Customer Scheduling Application".
 * @author Jason M. Sanborn
 * Software 2 -C195
 * Instructor -Juan Ruiz
 * Lambda 1: Located within both the Controller.apptScreen and Controller.customerScreen.  It is the same expression used three times to controller the population of combo boxes.
 * Lambda 2: Located within Controller.apptScreen to handle button activition for the included pop up windows.
 * Additional Report 3: Located within Controller.reportScreen - Listes all available First Level Divisions orginized by there owning Country.
 */

public class Main extends Application {

    /** Begins Application */

    @Override

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/loginScreen.fxml"));
        primaryStage.setTitle("Scheduling Application");
        primaryStage.getIcons().add(new Image(("file:Icon.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /** Main function call
     * Establish DataBase Connection
     */
    public static void main(String[] args) {
        jdbc.openConnection();
        launch(args);
        jdbc.closeConnection();
    }
}

