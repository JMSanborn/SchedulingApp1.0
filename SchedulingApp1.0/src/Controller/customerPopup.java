package Controller;

/** Controller "Customer Popup Window"
 * Creates a popup window to display available customer info
 * @author Jason M. Sanborn
 */

import Model.countries;
import Model.customers;
import Model.firstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.sql.SQLException;

public class customerPopup {
    @FXML public TableView<customers> customerTable;
    @FXML public TableColumn<customers, Integer> customerTableIDCol;
    @FXML public TableColumn<customers, String> customerTableNameCol;
    @FXML public TableColumn<customers, String> customerTableAddressCol;
    @FXML public TableColumn<customers, Integer> customerTableFirstLevelDivisionCol;
    @FXML public TableColumn<customers, String> customerTablePostalCodeCol;
    @FXML public TableColumn<customers, String> customerTablePhoneCol;

    public Button CloseCustomerList;

    public void initialize() throws SQLException {
        customerTableIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerTableNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTableAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerTableFirstLevelDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        customerTablePostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        customerTablePhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
        customerInfoToTableView();
    }

    public void customerInfoToTableView() throws SQLException {
        ObservableList<customers> allCustomersList = customers.getAllCustomers();
        customerTable.setItems(allCustomersList);
    }

    public void closeCustomerList(ActionEvent event) {
        Stage stage = (Stage) CloseCustomerList.getScene().getWindow();
        stage.close();

    }
}
