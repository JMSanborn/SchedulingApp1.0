package Controller;

/** Controller "Contact Popup Window"
 * Creates a popup window to display available contact info
 * @author Jason M. Sanborn
 */

import Model.contacts;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;

public class contactPopup {

    @FXML public TableView<contacts> contactTable;
    @FXML public TableColumn<contacts, Integer> contactTableIDCol;
    @FXML public TableColumn<contacts, String> contactTableNameCol;
    @FXML public TableColumn<contacts, String> contactTableEmailCol;
    @FXML public Button CloseContactList;

    public void initialize() throws SQLException {
        contactTableIDCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        contactTableNameCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        contactTableEmailCol.setCellValueFactory(new PropertyValueFactory<>("contactEmail"));
        contactInfoToTableView();
    }

    public void contactInfoToTableView() throws SQLException {
        ObservableList<contacts> allContactsList = contacts.getAllContacts();
        contactTable.setItems(allContactsList);
    }
    public void closeContactList(ActionEvent event) {
        Stage stage = (Stage) CloseContactList.getScene().getWindow();
        stage.close();
    }
}
