package Controller;

/** Controller "User Popup Window"
 * Creates a popup window to display available user info
 * @author Jason M. Sanborn
 */

import Model.users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;

public class userPopup {

    @FXML public TableView<users> userTable;
    @FXML public TableColumn<users, Integer> userTableIDCol;
    @FXML public TableColumn<users, String> userTableNameCol;
    @FXML public Button CloseUserList;

    public void initialize() throws SQLException {
        userTableIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        userTableNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userInfoToTableView();
    }

    public void userInfoToTableView() throws SQLException {
        ObservableList<users> allUsersList = users.getAllUsers();
        userTable.setItems(allUsersList);
    }
    public void closeUserList(ActionEvent event) {
        Stage stage = (Stage) CloseUserList.getScene().getWindow();
        stage.close();
    }
}

