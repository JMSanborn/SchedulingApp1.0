package Controller;

/** Controller "Report Screen"
 *  MVC to develop the GUI application scene.
 * @author Jason M. Sanborn
 * Allows user to view all appoints by type and month
 * Allows user to view a schedule for each contact
 *
 */

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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class reportScreen {

    @FXML Tab customerApptTab;
    @FXML TableView<appointmentsByType> customerApptTypeTable;
    @FXML TableColumn<appointmentsByType, String> customerApptTypeCol;
    @FXML TableColumn<appointmentsByType, Integer> customerApptTotalTypeCol;
    @FXML TableView<appointmentsByMonth> customerApptMonthTable;
    @FXML TableColumn<appointmentsByMonth, String> customerApptMonthCol;
    @FXML TableColumn<appointmentsByMonth, Integer> customerApptTotalMonthCol;
    @FXML Tab contactApptTab;
    @FXML TableView<appointments> contactApptTable;
    @FXML TableColumn<appointments, Integer> contactApptIDCol;
    @FXML TableColumn<appointments, String> contactApptTitleCol;
    @FXML TableColumn<appointments, String> contactApptTypeCol;
    @FXML TableColumn<appointments, String> contactApptDescripCol;
    @FXML TableColumn<appointments, LocalDateTime> contactApptStartCol;
    @FXML TableColumn<appointments, LocalDateTime> contactApptEndCol;
    @FXML TableColumn<appointments, Integer> contactApptCustomerIDCol;
    @FXML Label contactLabel;
    @FXML ComboBox<String> contactComboBox;
    @FXML Tab countriesFirstLevelDivisionTab;
    @FXML TableView<CustomerReportFirstLevelDivision> countriesFirstLevelDivisionTable;
    @FXML TableColumn<CustomerReportFirstLevelDivision, String> firstLevelDivisionCol;
    @FXML TableColumn<CustomerReportFirstLevelDivision, ArrayList<String>> countriesCol;


    /**
     *Initializes Report Tables and Tabs
     * @throws SQLException
     */

    public void initialize() throws SQLException {
        contactLabel.setVisible(false);
        contactComboBox.setVisible(false);
        contactComboBox.setEditable(true);
        contactComboBox.getEditor().setEditable(false);

        // First Report
        customerApptTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        customerApptTotalTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTotal"));
        customerApptMonthCol.setCellValueFactory(new PropertyValueFactory<>("appointmentMonth"));
        customerApptTotalMonthCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTotal"));

        //Second Report
        contactApptIDCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        contactApptTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        contactApptTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        contactApptDescripCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        contactApptStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        contactApptEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        contactApptCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        // Third Report
        firstLevelDivisionCol.setCellValueFactory(new PropertyValueFactory<>("firstLevelDivision"));
        countriesCol.setCellValueFactory(new PropertyValueFactory<>("countryList"));
    }

    /**
     * Produces list of appointment type and totals
     * creates instance of class and sets list for report table
     * @throws SQLException
     */
    public void appointmentTypeTotal() throws SQLException {
        String typeReport;
        int totalReport;
        ObservableList<appointments> allAppointmentData = appointments.getAllAppointments();
        ObservableList<String> appointmentTypes = FXCollections.observableArrayList();
        allAppointmentData.forEach(appointments -> appointmentTypes.add(appointments.getApptType()));
        ObservableList<String> duplicateMonths = FXCollections.observableArrayList();
        for (appointments appointment : allAppointmentData) {
            String type = appointment.getApptType();
            if (!duplicateMonths.contains(type)) {
                duplicateMonths.add(type);
            }
        }
        ObservableList<appointmentsByType> appointmentsByTypes = FXCollections.observableArrayList();
        for (String type : duplicateMonths) {
            int total = Collections.frequency(appointmentTypes, type);
            typeReport = type;
            totalReport = total;
            appointmentsByType typeInstance = new appointmentsByType(typeReport, totalReport);
            appointmentsByTypes.add(typeInstance);
        }
        customerApptTypeTable.setItems(appointmentsByTypes);
    }

    /**
     * Produces list of appointments per month
     * Eliminates Duplicates
     * creates instance of class and sets list for report table
     * @throws SQLException
     */
    public void appointmentMonthsTotal() throws SQLException {
        String monthReport;
        int totalReport;
        ObservableList<appointments> allAppointmentData = appointments.getAllAppointments();
        ObservableList<Month> appointmentMonths = FXCollections.observableArrayList();
        for (appointments appointment : allAppointmentData) {
            Month month = appointment.getStart().getMonth();
            appointmentMonths.add(month);
        }
        ObservableList<Month> duplicateMonths = FXCollections.observableArrayList();
        for (Month month : appointmentMonths) {
            if (!duplicateMonths.contains(month)) {
                duplicateMonths.add(month);
            }
        }
        ObservableList<appointmentsByMonth> appointmentsByMonths = FXCollections.observableArrayList();
        for (Month month : duplicateMonths) {
            int total = Collections.frequency(appointmentMonths, month);
            monthReport = month.name();
            totalReport = total;
            appointmentsByMonth monthInstance = new appointmentsByMonth(monthReport, totalReport);
            appointmentsByMonths.add(monthInstance);
        }
        customerApptMonthTable.setItems(appointmentsByMonths);
    }

    /**
     * calls above functions
     * @throws SQLException if exception has occurred
     */
    public void totalCustomerApptReport() throws SQLException {
        appointmentTypeTotal();
        appointmentMonthsTotal();
    }

    /**
     *initiates Contacts combo box for report selection
     * @throws SQLException
     */
    public void appointmentsPerContact() throws SQLException {
        contactLabel.setVisible(true);
        contactComboBox.setVisible(true);
        ObservableList<contacts> allContacts = contacts.getAllContacts();
        ObservableList<String> contactNames = FXCollections.observableArrayList();
        for (Model.contacts contacts : allContacts) {
            String contactName = contacts.getContactName();
            contactNames.add(contactName);
        }
        contactComboBox.setItems(contactNames);
    }

    /**
     * utilizes selected contact name from combo box
     * matches contact ID from all contact data
     * get appointments based on contact ID
     * @throws SQLException
     */
    public void getAppointmentsPerContact() throws SQLException {
        int selectedContactID = 0;
        appointments selectedContactAppointmentData;
        String selectedContactName = contactComboBox.getSelectionModel().getSelectedItem();
        if (selectedContactName != null) {

            ObservableList<contacts> allContactData = contacts.getAllContacts();
            for (contacts contact : allContactData) {
                if (selectedContactName.equals(contact.getContactName())) {
                    selectedContactID = contact.getContactID();
                }
            }
            ObservableList<appointments> allAppointmentData = appointments.getAllAppointments();
            ObservableList<appointments> appointmentDataForSelectedContact = FXCollections.observableArrayList();
            for (appointments appointment : allAppointmentData) {
                if (selectedContactID == appointment.getContactID()) {
                    selectedContactAppointmentData = appointment;
                    appointmentDataForSelectedContact.add(selectedContactAppointmentData);
                }
            }
            contactApptTable.setItems(appointmentDataForSelectedContact);
        }
    }



    /**
     * Matches First Level Divisions with owning country
     *Lambda 2
     * @throws SQLException
     */
    public void countriesReport() throws SQLException {
        String firstLevelDivisionToSet ;
        ArrayList<String> countryListToSet;
        CustomerReportFirstLevelDivision record;
        String previousSetDivisionName = "";

        ObservableList<firstLevelDivisions> allFirstLevelDivisionData = firstLevelDivisions.getAllFirstLevelDivisions();
        ObservableList<countries> allCountryData = countries.getAllCountries();
        ObservableList<CustomerReportFirstLevelDivision> observableListToSet = FXCollections.observableArrayList();

        for (firstLevelDivisions firstLevelDivision : allFirstLevelDivisionData) {
            countryListToSet = new ArrayList<>();
            for (countries country : allCountryData) {
                if (firstLevelDivision.getCountry_ID() == country.getCountryID()) {
                    firstLevelDivisionToSet = firstLevelDivision.getDivisionName();
                    countryListToSet.add(country.getCountryName());

                    record = new CustomerReportFirstLevelDivision(firstLevelDivisionToSet, countryListToSet);
                    if (!record.getFirstLevelDivision().equals(previousSetDivisionName)) {
                        observableListToSet.add(record);
                        previousSetDivisionName = record.getFirstLevelDivision();
                    }
                }
            }

        }
        countriesFirstLevelDivisionTable.setItems(observableListToSet);
    }


    public void openCustomerINFO(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/customerScreen.fxml")));
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(new Scene(root));
    }

    public void openAppointmentsINFO(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/apptScreen.fxml")));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root));
    }

    public void openMainScreen(ActionEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/mainScreen.fxml")));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root, 950, 700));
        appointments login = new appointments();
        login.appointmentCheck();
    }
}
