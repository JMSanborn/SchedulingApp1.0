package Controller;

/** Controller "Customer Screen"
 *  MVC to develop the GUI application scene.
 * @author Jason M. Sanborn
 *Allows user to view, update, and delete customers
*/

import Database.jdbcQuery;
import Database.jdbc;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.swing.JOptionPane;

public class customerScreen {
    @FXML public TableView<customers> customerTable;
    @FXML public TableColumn<customers, Integer> customerTableIDCol;
    @FXML public TableColumn<customers, String> customerTableNameCol;
    @FXML public TableColumn<customers, String> customerTableAddressCol;
    @FXML public TableColumn<customers, Integer> customerTableFirstLevelDivisionCol;
    @FXML public TableColumn<customers, String> customerTablePostalCodeCol;
    @FXML public TableColumn<customers, String> customerTablePhoneCol;
    @FXML public Button customerAddButton;
    @FXML public Button customerResetButton;
    @FXML public Button customerUpdateButton;
    @FXML public Button customerDeleteButton;
    @FXML public TextField customerIDTextField;
    @FXML public TextField customerNameTextField;
    @FXML public TextField customerAddressTextField;
    @FXML public TextField customerPostalCodeTextField;
    @FXML public TextField customerPhoneNumberTextField;
    @FXML public ComboBox<String> customerCountryComboBox;
    @FXML public ComboBox<String> customerFirstLevelDivisionComboBox;

    /**
     * Initializes and populates tables and combo boxes
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        customerTableIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerTableNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTableAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerTableFirstLevelDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        customerTablePostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        customerTablePhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));

        /**
         * lambda Expression 1 -part1  to simplify combo box population
         */
        ObservableList<countries>  countriesObservableList = countries.getAllCountries();
        ObservableList<String> allCountryNames = FXCollections.observableArrayList();
        countriesObservableList.forEach(countries -> allCountryNames.add(countries.getCountryName()));
        //for (countries country : allCountries) {
            //countryNames.add(country.getCountryName());
        //}
        customerCountryComboBox.setItems(allCountryNames);
        customerCountryComboBox.setEditable(true);
        customerCountryComboBox.getEditor().setEditable(false);

        /**
         * lambda Expression 1 -part2  to simplify combo box population
         */
        ObservableList<firstLevelDivisions> firstLevelDivisionsObservableList = firstLevelDivisions.getAllFirstLevelDivisions();
        ObservableList<String> firstLevelDivisionAllNames = FXCollections.observableArrayList();
        firstLevelDivisionsObservableList.forEach(firstLevelDivisions-> firstLevelDivisionAllNames.add(firstLevelDivisions.getDivisionName()));
        //for (Model.firstLevelDivisions firstLevelDivisions : allFirstLevelDivisions){
            //firstLevelDivisionAllNames.add(firstLevelDivisions.getDivisionName());
        //}
        customerFirstLevelDivisionComboBox.setItems(firstLevelDivisionAllNames);
        customerFirstLevelDivisionComboBox.setEditable(true);
        customerFirstLevelDivisionComboBox.getEditor().setEditable(false);

        customerInfoToTableView();
    }

    /**
     * populates  Customer info to table
     * @throws SQLException
     */
    public void customerInfoToTableView() throws SQLException {
        ObservableList<customers> allCustomersList = customers.getAllCustomers();
        customerTable.setItems(allCustomersList);
    }

    /**
     * populates customer data to text boxes
     * initializes division combo box and country combo box
     * @throws SQLException
     */
    public void customerInfoToInputBoxes() throws SQLException {
        customers selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            ObservableList<countries> allCountries = countries.getAllCountries();
            ObservableList<firstLevelDivisions> allFirstLevelDivisions = firstLevelDivisions.getAllFirstLevelDivisions();
            ObservableList<String> firstLevelDivisionAllNames = FXCollections.observableArrayList();
            for (firstLevelDivisions firstLevelDivision : allFirstLevelDivisions) {
                firstLevelDivisionAllNames.add(firstLevelDivision.getDivisionName());
            }
            customerFirstLevelDivisionComboBox.setItems(firstLevelDivisionAllNames);
            customerIDTextField.setText(String.valueOf((selectedCustomer.getCustomerID())));
            customerNameTextField.setText(selectedCustomer.getCustomerName());
            customerAddressTextField.setText(selectedCustomer.getCustomerAddress());
            customerPostalCodeTextField.setText(selectedCustomer.getCustomerPostalCode());
            customerPhoneNumberTextField.setText(selectedCustomer.getCustomerPhoneNumber());
            int divisionIDToSet = selectedCustomer.getDivisionID();
            String divisionNameToSet = "";
            int countryIDToSet;
            String countryNameToSet = "";
            for (firstLevelDivisions firstLevelDivision : allFirstLevelDivisions) {
                if (divisionIDToSet == firstLevelDivision.getDivisionID()) {
                    divisionNameToSet = firstLevelDivision.getDivisionName();
                    countryIDToSet = firstLevelDivision.getCountry_ID();
                    for (countries country : allCountries) {
                        if (countryIDToSet == country.getCountryID()) {
                            countryNameToSet = country.getCountryName();
                        }
                    }
                }
            }
            customerFirstLevelDivisionComboBox.setValue(divisionNameToSet);
            customerCountryComboBox.setValue(countryNameToSet);
            customerAddButton.setDisable(true);
            customerUpdateButton.setDisable(false);
            customerDeleteButton.setDisable(false);
            customerResetButton.setDisable(false);
        }
    }

    /**
     * First Level Division Combo Box.
     * Populates according to selected Country.
     * @throws SQLException
     */
    public void firstLevelDivisionComboBox() throws SQLException {
        ObservableList<firstLevelDivisions> allFirstLevelDivisions = firstLevelDivisions.getAllFirstLevelDivisions();
        ObservableList<String> firstLevelDivisionNamesUS = FXCollections.observableArrayList();
        ObservableList<String> firstLevelDivisionNamesUK = FXCollections.observableArrayList();
        ObservableList<String> firstLevelDivisionNamesCanada = FXCollections.observableArrayList();
        for (firstLevelDivisions firstLevelDivision : allFirstLevelDivisions) {
            if (firstLevelDivision.getCountry_ID() == 1) {
                firstLevelDivisionNamesUS.add(firstLevelDivision.getDivisionName());
            } else if (firstLevelDivision.getCountry_ID() == 2) {
                firstLevelDivisionNamesUK.add(firstLevelDivision.getDivisionName());
            } else if (firstLevelDivision.getCountry_ID() == 3) {
                firstLevelDivisionNamesCanada.add(firstLevelDivision.getDivisionName());
            }
        }
        String selectedCountry = customerCountryComboBox.getSelectionModel().getSelectedItem();
        if (selectedCountry.equals("U.S")) {
            customerFirstLevelDivisionComboBox.setItems(firstLevelDivisionNamesUS);
        } else if (selectedCountry.equals("UK")) {
            customerFirstLevelDivisionComboBox.setItems(firstLevelDivisionNamesUK);
        } else if (selectedCountry.equals("Canada")) {
            customerFirstLevelDivisionComboBox.setItems(firstLevelDivisionNamesCanada);
        }
    }

    /**
     *customer info box rest after action
     */
    public void resetInputBoxes() {
        customerTable.getSelectionModel().clearSelection();
        customerIDTextField.clear();
        customerIDTextField.setDisable(true);
        customerNameTextField.clear();
        customerAddressTextField.clear();
        customerPostalCodeTextField.clear();
        customerPhoneNumberTextField.clear();
        customerCountryComboBox.setValue("");
        customerFirstLevelDivisionComboBox.setValue("");
        customerAddButton.setDisable(false);
        customerUpdateButton.setDisable(true);
        customerDeleteButton.setDisable(true);
        customerResetButton.setDisable(true);
    }

    /**
     * verifies that all inputs have value
     * auto-generates customer ID
     * matches division name string with division ID
     * gets and executes prepared insert statement
     * calls appropriate functions to display data and reset boxes
     * @throws SQLException
     */
    public void addCustomerInfo(ActionEvent event) throws SQLException{
        if (!customerNameTextField.getText().equals("") && !customerAddressTextField.getText().equals("") &&
                !customerCountryComboBox.getValue().equals("") && !customerFirstLevelDivisionComboBox.getValue().equals("") &&
                !customerPostalCodeTextField.getText().equals("") && !customerPhoneNumberTextField.getText().equals("")) {
            int lastID = 0;
            ObservableList<customers> allCustomersList = customers.getAllCustomers();
            for (customers customer : allCustomersList) {
                lastID = customer.getCustomerID();
            }
            int idToAdd = lastID + 1;
            String nameToAdd = customerNameTextField.getText();
            String addressToAdd = customerAddressTextField.getText();
            String postalCodeToAdd = customerPostalCodeTextField.getText();
            String phoneNumberToAdd = customerPhoneNumberTextField.getText();
            int firstLevelDivisionIDToAdd = 0;
            String selectedFirstLevelDivision = customerFirstLevelDivisionComboBox.getSelectionModel().getSelectedItem();
            ObservableList<firstLevelDivisions> allFirstLevelDivisions = firstLevelDivisions.getAllFirstLevelDivisions();
            for (firstLevelDivisions firstLevelDivision : allFirstLevelDivisions) {
                if (selectedFirstLevelDivision.equals(firstLevelDivision.getDivisionName())) {
                    firstLevelDivisionIDToAdd = firstLevelDivision.getDivisionID();
                }
            }
            LocalDateTime createdDateToAdd = LocalDateTime.now();
            String createdByToAdd = users.getUserName();
            Timestamp lastUpdateToAdd = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdatedByToAdd = users.getUserName();
            String insertStatement = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, " +
                    "Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";
            jdbcQuery.setPreparedStatement(jdbc.getConnection(), insertStatement);
            PreparedStatement ps = jdbcQuery.getPreparedStatement();
            ps.setInt(1, idToAdd);
            ps.setString(2, nameToAdd);
            ps.setString(3, addressToAdd);
            ps.setString(4, postalCodeToAdd);
            ps.setString(5, phoneNumberToAdd);
            ps.setTimestamp(6, Timestamp.valueOf(createdDateToAdd));
            ps.setString(7, createdByToAdd);
            ps.setTimestamp(8, lastUpdateToAdd);
            ps.setString(9, lastUpdatedByToAdd);
            ps.setInt(10, firstLevelDivisionIDToAdd);
            ps.execute();
            JOptionPane.showMessageDialog(null,
                    "New customer to add: " + nameToAdd,
                    "Customer added to database", JOptionPane.INFORMATION_MESSAGE);
            customerInfoToTableView();
            resetInputBoxes();
        } else JOptionPane.showMessageDialog(null, "Customer Data Incomplete",
                "New customer not added", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Populates Customer info in the corresponding text box
     * Writes updated info to database
     * @throws SQLException
     */
    public void updateCustomerInfo(ActionEvent event) throws SQLException {
        int idToUpdate = Integer.parseInt(customerIDTextField.getText());
        String nameToUpdate = customerNameTextField.getText();
        String addressToUpdate = customerAddressTextField.getText();
        String postalCodeToUpdate = customerPostalCodeTextField.getText();
        String phoneNumberToUpdate = customerPhoneNumberTextField.getText();
        String firstLevelDivisionStringToUpdate = customerFirstLevelDivisionComboBox.getValue();
        int firstLevelDivisionIntToUpdate = 0;
        ObservableList<firstLevelDivisions> allFirstLevelDivisions = firstLevelDivisions.getAllFirstLevelDivisions();
        for (firstLevelDivisions firstLevelDivision : allFirstLevelDivisions) {
            if (firstLevelDivisionStringToUpdate.equals(firstLevelDivision.getDivisionName())) {
                firstLevelDivisionIntToUpdate = firstLevelDivision.getDivisionID();
            }
        }
        Timestamp lastUpdateToUpdate = Timestamp.valueOf(LocalDateTime.now());
        String lastUpdatedByToUpdate = users.getUserName();
        String updateStatement = "UPDATE customers SET Customer_ID = ?, Customer_Name = ?, Address = ?, " +
                "Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, " +
                "Division_ID = ? WHERE Customer_ID = ?";
        jdbcQuery.setPreparedStatement(jdbc.getConnection(), updateStatement);
        PreparedStatement ps = jdbcQuery.getPreparedStatement();
        ps.setInt(1, idToUpdate);
        ps.setString(2, nameToUpdate);
        ps.setString(3, addressToUpdate);
        ps.setString(4, postalCodeToUpdate);
        ps.setString(5, phoneNumberToUpdate);
        ps.setTimestamp(6, lastUpdateToUpdate);
        ps.setString(7, lastUpdatedByToUpdate);
        ps.setInt(8, firstLevelDivisionIntToUpdate);
        ps.setInt(9, idToUpdate);
        ps.execute();
        JOptionPane.showMessageDialog(null,
                "Customer information to update: " + nameToUpdate,
                "Customer information updated", JOptionPane.INFORMATION_MESSAGE);
        customerInfoToTableView();
        resetInputBoxes();
    }

    /**
     * Populates Customer info in the corresponding text box
     * Checks if customer has any upcoming appointments
     * Verifies deletion of customer
     * @throws SQLException
     */
public void deleteCustomerInfo() throws SQLException {

    String deleteStatement = "DELETE FROM customers WHERE Customer_ID = ?";
    jdbcQuery.setPreparedStatement(jdbc.getConnection(), deleteStatement);
    PreparedStatement ps = jdbcQuery.getPreparedStatement();
    int customerIDToDelete = customerTable.getSelectionModel().getSelectedItem().getCustomerID();
    ObservableList<appointments> appointmentsObservableList = appointments.getAllAppointments();
    for (appointments appointment : appointmentsObservableList) {
        int customerIDToCheck = appointment.getCustomerID();
        int appointmentIDToDelete = appointment.getApptID();
        String appointmentTypeToDisplay = appointment.getApptType();
        if (customerIDToDelete == customerIDToCheck) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Customer");
            alert.setHeaderText("Customer has upcoming appointment, would you still like to delete? ");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                String deleteStatementAppointments = "DELETE FROM appointments WHERE Appointment_ID = ?";
                jdbcQuery.setPreparedStatement(jdbc.getConnection(), deleteStatementAppointments);
                PreparedStatement psAppointments = jdbcQuery.getPreparedStatement();
                psAppointments.setInt(1, appointmentIDToDelete);
                psAppointments.execute();
                JOptionPane.showMessageDialog(null,
                        "\nAppointment_ID: " + appointmentIDToDelete +
                                "\nType of Appointment: " + appointmentTypeToDisplay,
                        "Appointment Deleted From Database", JOptionPane.INFORMATION_MESSAGE);
                ps.setInt(1, customerIDToDelete);
                ps.execute();
                customerInfoToTableView();
                resetInputBoxes();
                JOptionPane.showMessageDialog(null,
                        "Customer ID: " + customerIDToDelete,
                        "Customer deleted from database", JOptionPane.INFORMATION_MESSAGE);

            } else {
                alert.close();
            }
        }
    }
}

    public void openMainScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/mainScreen.fxml")));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root, 950, 700));
    }

    public void openAppointmentScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/apptScreen.fxml")));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root));
    }

    public void openReportScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/reportScreen.fxml")));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root, 950, 700));
    }
}

