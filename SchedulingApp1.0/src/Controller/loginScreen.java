package Controller;

/** Controller "Login Screen"
 * Login validation for application.
 * Language adaptation based on ZoneID.
 * @author Jason M. Sanborn
 */

import Database.*;
import Model.appointments;
import javafx.fxml.FXML;
import java.net.URL;
import java.time.ZoneId;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class loginScreen  implements Initializable {

    @FXML public TextField Username;
    @FXML public TextField Password;
    @FXML public Button LogInButton;
    @FXML public Label UserLocation;
    @FXML public Label loginLabel;
    @FXML public Label UserPassword;
    @FXML public Label UserID;
    @FXML public Label userLocationLabel;
    @FXML private String errorHeader;
    @FXML private String errorTitle;
    @FXML private String errorText;


    public void LogIn(ActionEvent event) throws Exception {
        String userName = Username.getText();
        String userPassword = Password.getText();
        Integer userID = null;
        boolean checkedUser = jdbcValidate.validateLogin(userID, userName, userPassword);
        if (checkedUser) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/mainScreen.fxml")));
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(new Scene(root));
            appointments login = new appointments();
            login.appointmentCheck();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(errorTitle);
            alert.setHeaderText(errorHeader);
            alert.setContentText(errorText);
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Locale locale = Locale.getDefault();
        rb = ResourceBundle.getBundle("Language", locale);
        UserID.setText(rb.getString("UserID"));
        UserPassword.setText(rb.getString("UserPassword"));
        LogInButton.setText(rb.getString("logInButton"));
        loginLabel.setText(rb.getString("loginLabel"));
        UserLocation.setText(rb.getString("UserLocation"));
        errorHeader = rb.getString("errorheader");
        errorTitle = rb.getString("errortitle");
        errorText = rb.getString("errortext");
        zoneCheck();

}
    public void zoneCheck() {
        ZoneId currentZone = ZoneId.systemDefault();
        userLocationLabel.setText("User Location: " + currentZone);
    }
}

