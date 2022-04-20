package Controller;

/** Controller "Appointment Screen"
 *  MVC to develop the GUI application scene.
 * @author Jason M. Sanborn
 *Allows user to view, update, and delete appointments
 * Includes Lambda 1 part3 and Lambda 2
*/

import Database.*;
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
import javax.swing.JOptionPane;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.function.Function;

public class apptScreen {

    @FXML public Tab appTableMonthTabButton;
    @FXML public TableView<appointments> appMonthTable;
    @FXML public TableColumn<appointments, Integer> appMonthTableAppointmentIDCol;
    @FXML public TableColumn<appointments, String> appMonthTableTitleCol;
    @FXML public TableColumn<appointments, String> appMonthTableDescriptionCol;
    @FXML public TableColumn<appointments, String> appMonthTableLocationCol;
    @FXML public TableColumn<appointments, Integer> appMonthTableContactCol;
    @FXML public TableColumn<appointments, String> appMonthTableTypeCol;
    @FXML public TableColumn<appointments, LocalDateTime> appMonthTableStartDateAndTimeCol;
    @FXML public TableColumn<appointments, LocalDateTime> appMonthTableEndDateAndTimeCol;
    @FXML public TableColumn<appointments, Integer> appMonthTableCustomerIDCol;
    @FXML public TableColumn<appointments, Integer> appMonthTableUserIDCol;

    @FXML public Tab appTableViewWeekTabButton;
    @FXML public TableView<appointments> appWeekTable;
    @FXML public TableColumn<appointments, Integer> appWeekTableAppointmentIDCol;
    @FXML public TableColumn<appointments, String> appWeekTableTitleCol;
    @FXML public TableColumn<appointments, String> appWeekTableDescriptionCol;
    @FXML public TableColumn<appointments, String> appWeekTableLocationCol;
    @FXML public TableColumn<appointments, Integer> appWeekTableContactCol;
    @FXML public TableColumn<appointments, String> appWeekTableTypeCol;
    @FXML public TableColumn<appointments, LocalDateTime> appWeekTableStartDateAndTimeCol;
    @FXML public TableColumn<appointments, LocalDateTime> appWeekTableEndDateAndTimeCol;
    @FXML public TableColumn<appointments, Integer> appWeekTableCustomerIDCol;
    @FXML public TableColumn<appointments, Integer>appWeekTableUserIDCol;

    @FXML public Tab appTableViewAllTabButton;
    @FXML public TableView<appointments> appAllTable;
    @FXML public TableColumn<appointments, Integer> appAllTableAppointmentIDCol;
    @FXML public TableColumn<appointments, String> appAllTableTitleCol;
    @FXML public TableColumn<appointments, String> appAllTableDescriptionCol;
    @FXML public TableColumn<appointments, String> appAllTableLocationCol;
    @FXML public TableColumn<appointments, Integer> appAllTableContactCol;
    @FXML public TableColumn<appointments, String> appAllTableTypeCol;
    @FXML public TableColumn<appointments, LocalDateTime> appAllTableStartDateAndTimeCol;
    @FXML public TableColumn<appointments, LocalDateTime> appAllTableEndDateAndTimeCol;
    @FXML public TableColumn<appointments, Integer> appAllTableCustomerIDCol;
    @FXML public TableColumn<appointments, Integer>appAllTableUserIDCol;
    @FXML public Label eSTTime;


    @FXML Button addAppointmentButton;
    @FXML Button updateAppointmentButton;
    @FXML Button deleteAppointmentButton;
    @FXML Button resetAppointmentButton;
    @FXML TextField appointmentTitleTextField;
    @FXML TextField appointmentDescriptionTextField;
    @FXML TextField appointmentLocationTextField;
    @FXML TextField appointmentIDTextField;
    @FXML TextField appointmentTypeTextField;
    @FXML DatePicker appointmentStartDatePicker;
    @FXML DatePicker appointmentEndDatePicker;
    @FXML ComboBox<String> appointmentStartTimeComboBox;
    @FXML ComboBox<String> appointmentEndTimeComboBox;
    @FXML TextField appointmentCustomerIDTextField;
    @FXML TextField appointmentUserIDTextField;
    @FXML public ComboBox<String> appointmentContactComboBox;
    @FXML public Label currentTimeZone;

    // used with lambda #2
    @FXML public Button customerListPopUp;
    @FXML public Button userListPopUp;
    @FXML public Button contactListPopUp;
    int clickedButton = -1;


    /**
     *Builds tables for monthly, weekly and all
     * set up combo boxes
     * initiates lambda expression 1
     * @throws SQLException
     */
    public void initialize() throws SQLException, ParseException {
        appMonthTableAppointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        appMonthTableTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        appMonthTableDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        appMonthTableLocationCol.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        appMonthTableContactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appMonthTableTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        appMonthTableStartDateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appMonthTableEndDateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        appMonthTableCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appMonthTableUserIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

        appWeekTableAppointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        appWeekTableTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        appWeekTableDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        appWeekTableLocationCol.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        appWeekTableContactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appWeekTableTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        appWeekTableStartDateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appWeekTableEndDateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        appWeekTableCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appWeekTableUserIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

        appAllTableAppointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        appAllTableTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        appAllTableDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        appAllTableLocationCol.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        appAllTableContactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appAllTableTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        appAllTableStartDateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appAllTableEndDateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        appAllTableCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appAllTableUserIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));


        /**
         * lambda Expression 1 -part3  to simplify combo box population
         */
        ObservableList<contacts> contactsObservableList = contacts.getAllContacts();
        ObservableList<String> allContactsNames = FXCollections.observableArrayList();
        contactsObservableList.forEach(contacts -> allContactsNames.add(contacts.getContactName()));
        appointmentContactComboBox.setItems(allContactsNames);
        appointmentContactComboBox.setEditable(true);
        appointmentContactComboBox.getEditor().setEditable(false);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        ObservableList<String> appointmentTimesDuringDay = FXCollections.observableArrayList();
        LocalTime firstAppointmentTimeLDT = LocalTime.MIN.plusHours(0);
        LocalTime lastAppointmentTimeLDT = LocalTime.MAX.minusHours(0).minusMinutes(30);
        while (firstAppointmentTimeLDT.isBefore(LocalTime.from(lastAppointmentTimeLDT))) {
            appointmentTimesDuringDay.add(dateTimeFormatter.format(firstAppointmentTimeLDT));
            firstAppointmentTimeLDT = firstAppointmentTimeLDT.plusMinutes(30);
        }
        appointmentStartTimeComboBox.setItems(appointmentTimesDuringDay);
        appointmentStartTimeComboBox.setEditable(true);
        appointmentStartTimeComboBox.getEditor().setEditable(false);
        appointmentEndTimeComboBox.setItems(appointmentTimesDuringDay);
        appointmentEndTimeComboBox.setEditable(true);
        appointmentEndTimeComboBox.getEditor().setEditable(false);
        appointmentInfoToTableView();
        currentTimeZone();
        eSTTime();
    }

    /**
     * Populates appointment data to appointmentTableView.
     * Separates scheduled appoint into the associated tab view.
     * @throws SQLException
     */
    public void appointmentInfoToTableView() throws SQLException {
        ObservableList<appointments> allAppointmentsList = appointments.getAllAppointments();
        ObservableList<appointments> appointmentsMonth = FXCollections.observableArrayList();
        ObservableList<appointments> appointmentsWeek = FXCollections.observableArrayList();

        LocalDateTime localD = LocalDateTime.now();
        LocalTime zeroHoursAndMinutes = LocalTime.of(0, 00);
        LocalDateTime zeroLocalDT = LocalDateTime.of(LocalDate.from(localD), zeroHoursAndMinutes);
        LocalDateTime finalHourDT = LocalDateTime.of(LocalDate.from(localD), LocalTime.of(23,59));
        LocalDateTime currentMonthStart = zeroLocalDT.with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime currentMonthEnd = finalHourDT.with(TemporalAdjusters.lastDayOfMonth());
        TemporalField fieldLOCAL = WeekFields.of(Locale.getDefault()).dayOfWeek();
        LocalDateTime currentWeekStart = zeroLocalDT.with(fieldLOCAL, 1);
        LocalDateTime currentWeekEnd = finalHourDT.with(fieldLOCAL, 7);

        for (appointments appointment : allAppointmentsList) {
            if (appointment.getEnd().isAfter(currentMonthStart) && appointment.getEnd().isBefore(currentMonthEnd)) {
                appointmentsMonth.add(appointment);
            }
            if (appointment.getEnd().isAfter(currentWeekStart) && appointment.getEnd().isBefore(currentWeekEnd)) {
                appointmentsWeek.add(appointment);
            }
        }
        appMonthTable.setItems(appointmentsMonth);
        appWeekTable.setItems(appointmentsWeek);
        appAllTable.setItems(allAppointmentsList);
    }

    /**
     * Populates selected appointment info to the associated text boxes
     * @throws SQLException
     */
    public void appointmentInfoToInputBoxes() throws SQLException {
        appointments selectedAppointment = null;
        if (appTableMonthTabButton.isSelected()) {
            selectedAppointment = appMonthTable.getSelectionModel().getSelectedItem();
        }
        if (appTableViewWeekTabButton.isSelected()) {
            selectedAppointment = appWeekTable.getSelectionModel().getSelectedItem();
        }
        if (appTableViewAllTabButton.isSelected()) {
            selectedAppointment = appAllTable.getSelectionModel().getSelectedItem();
        }
        if (selectedAppointment != null) {
            appointmentIDTextField.setText(String.valueOf((selectedAppointment.getApptID())));
            appointmentTitleTextField.setText(selectedAppointment.getApptTitle());
            appointmentDescriptionTextField.setText(selectedAppointment.getApptDescription());
            appointmentLocationTextField.setText(selectedAppointment.getApptLocation());
            int contactIDToDisplay = selectedAppointment.getContactID();
            String contactNameToDisplay = "";
            ObservableList<contacts> contactsObservableList = contacts.getAllContacts();
            for (contacts contact : contactsObservableList) {
                if (contactIDToDisplay == contact.getContactID()) {
                    contactNameToDisplay = contact.getContactName();
                }
            }
            appointmentContactComboBox.setValue(contactNameToDisplay);
            appointmentTypeTextField.setText(selectedAppointment.getApptType());
            appointmentStartDatePicker.setValue(selectedAppointment.getStart().toLocalDate());
            appointmentEndDatePicker.setValue(selectedAppointment.getEnd().toLocalDate());
            appointmentStartTimeComboBox.setValue(selectedAppointment.getStart().toLocalTime().toString());
            appointmentEndTimeComboBox.setValue(selectedAppointment.getEnd().toLocalTime().toString());
            appointmentCustomerIDTextField.setText(String.valueOf(selectedAppointment.getCustomerID()));
            appointmentUserIDTextField.setText(String.valueOf(selectedAppointment.getUserID()));
            addAppointmentButton.setDisable(true);
            updateAppointmentButton.setDisable(false);
            deleteAppointmentButton.setDisable(false);
            resetAppointmentButton.setDisable(false);
        }
    }

    /**
     * Displays the Users current Time Zone
     */
    public void currentTimeZone() {
        TimeZone zone = TimeZone.getDefault();
        String name = zone.getDisplayName();
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM-dd HH:mm");
        String formattedDate = myDateObj.format(myFormatObj);
        currentTimeZone.setText("User Current Time Zone: " + name +" "+ formattedDate);
    }

    /**
     * Displays the Eastern Standard Time Zone
     */
    public void eSTTime(){
        ZonedDateTime zoned = ZonedDateTime.now();
        LocalDateTime local = zoned.toLocalDateTime();
        ZonedDateTime newZoned = ZonedDateTime.now(ZoneId.of("America/New_York"));
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM-dd HH:mm");
        String formattedDate = newZoned.format(myFormatObj);
        eSTTime.setText("Business Hours Time Zone: " + "Eastern Standard Time" +" "+ formattedDate);
    }
    /**
     * Resets input boxes after successful Add or update action.
     */
    public void resetInputBoxes() {
        appMonthTable.getSelectionModel().clearSelection();
        appointmentTitleTextField.clear();
        appointmentDescriptionTextField.clear();
        appointmentLocationTextField.clear();
        appointmentIDTextField.clear();
        appointmentTypeTextField.clear();
        appointmentStartDatePicker.setValue(null);
        appointmentEndDatePicker.setValue(null);
        appointmentStartTimeComboBox.setValue("");
        appointmentEndTimeComboBox.setValue("");
        appointmentCustomerIDTextField.clear();
        appointmentUserIDTextField.clear();
        appointmentContactComboBox.setValue("");
        addAppointmentButton.setDisable(false);
        updateAppointmentButton.setDisable(true);
        deleteAppointmentButton.setDisable(true);
        resetAppointmentButton.setDisable(true);
    }

    /**
     * Adds new appointment to database
     * validate text inputs
     * validates time and date
     * @throws SQLException
     */
    public void addAppointmentInfo() throws SQLException {

        if (!appointmentTitleTextField.getText().equals("") && !appointmentDescriptionTextField.getText().equals("") &&
                !appointmentLocationTextField.getText().equals("") && (appointmentContactComboBox.getValue() != null) &&
                !appointmentTypeTextField.getText().equals("") && !appointmentCustomerIDTextField.getText().equals("") &&
                !appointmentUserIDTextField.getText().equals("") && (appointmentStartDatePicker.getValue() != null) &&
                (appointmentEndDatePicker.getValue() != null) && !appointmentStartTimeComboBox.getValue().equals("") &&
                !appointmentEndTimeComboBox.getValue().equals("")) {

            ObservableList<customers> allCustomerData = customers.getAllCustomers();
            ObservableList<Integer> allCustomerIDs = FXCollections.observableArrayList();
            for (customers customer : allCustomerData) {
                allCustomerIDs.add(customer.getCustomerID());
            }
            if (!allCustomerIDs.contains(Integer.parseInt(appointmentCustomerIDTextField.getText()))) {
                JOptionPane.showMessageDialog(null, "Please verify Customer Id",
                        "Appointment will not be added" , JOptionPane.ERROR_MESSAGE);
                return;
            }
            ObservableList<users> allUserData = users.getAllUsers();
            ObservableList<Integer> allUserIDs = FXCollections.observableArrayList();
            for (users user : allUserData) {
                allUserIDs.add(user.getUserID());
            }
            if (!allUserIDs.contains(Integer.parseInt(appointmentUserIDTextField.getText()))) {
                JOptionPane.showMessageDialog(null, "Please verify User ID",
                        "Appointment will not be added" , JOptionPane.ERROR_MESSAGE);
                return;
            }

            int lastAppointmentID = 0;
            ObservableList<appointments> allAppointmentsList = appointments.getAllAppointments();
            for (appointments appointment : allAppointmentsList) {
                lastAppointmentID = appointment.getApptID();
            }
            int appointmentIDToAdd = lastAppointmentID + 1;
            String titleToAdd = appointmentTitleTextField.getText();
            String descriptionToAdd = appointmentDescriptionTextField.getText();
            String locationToAdd = appointmentLocationTextField.getText();
            String typeToAdd = appointmentTypeTextField.getText();
            String contactNameToAdd = appointmentContactComboBox.getValue();
            int contactIDToAdd = 0;
            ObservableList<contacts> contactsObservableList = contacts.getAllContacts();
            for (contacts contact : contactsObservableList) {
                if (contactNameToAdd.equals(contact.getContactName())) {
                    contactIDToAdd = contact.getContactID();
                }
            }
            LocalDate startLocalDate = appointmentStartDatePicker.getValue();
            LocalDate endLocalDate = appointmentEndDatePicker.getValue();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime startLocalTime = LocalTime.parse(appointmentStartTimeComboBox.getValue(), timeFormatter);
            LocalTime endLocalTime = LocalTime.parse(appointmentEndTimeComboBox.getValue(), timeFormatter);
            LocalDateTime startLocalDateTimeToAdd = LocalDateTime.of(startLocalDate, startLocalTime);
            LocalDateTime endLocalDateTimeToAdd = LocalDateTime.of(endLocalDate, endLocalTime);

            ZonedDateTime startLDTToZDT = ZonedDateTime.of(startLocalDateTimeToAdd, ZoneId.systemDefault());
            ZonedDateTime startZDTToZDTEST = startLDTToZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
            LocalTime startAppointmentTimeToCheck = startZDTToZDTEST.toLocalTime();
            DayOfWeek startAppointmentDayToCheck = startZDTToZDTEST.toLocalDate().getDayOfWeek();
            int startAppointmentDayToCheckInt = startAppointmentDayToCheck.getValue();

            ZonedDateTime endLDTToZDT = ZonedDateTime.of(endLocalDateTimeToAdd, ZoneId.systemDefault());
            ZonedDateTime endZDTToZDTEST = endLDTToZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
            LocalTime endAppointmentTimeToCheck = endZDTToZDTEST.toLocalTime();
            DayOfWeek endAppointmentDayToCheck = endZDTToZDTEST.toLocalDate().getDayOfWeek();
            int endAppointmentDayToCheckInt = endAppointmentDayToCheck.getValue();


            LocalTime startOfBusinessHours = LocalTime.of(8, 0, 0);
            LocalTime endOfBusinessHours = LocalTime.of(22, 00, 0);
            int startOfWeekInt = DayOfWeek.MONDAY.getValue();
            int endOfWeekInt = DayOfWeek.FRIDAY.getValue();

            if (startAppointmentTimeToCheck.isBefore(startOfBusinessHours) ||
                    startAppointmentTimeToCheck.isAfter(endOfBusinessHours) ||
                    endAppointmentTimeToCheck.isBefore(startOfBusinessHours) ||
                    endAppointmentTimeToCheck.isAfter(endOfBusinessHours) ||
                    startAppointmentDayToCheckInt < startOfWeekInt ||
                        startAppointmentDayToCheckInt > endOfWeekInt ||
                        endAppointmentDayToCheckInt < startOfWeekInt ||
                        endAppointmentDayToCheckInt > endOfWeekInt ||
                    startAppointmentDayToCheckInt != endAppointmentDayToCheckInt){
                JOptionPane.showMessageDialog(null, "Appointment is outside of business hours\n" +
                                "Please schedule between 08:00 and 22:00 EST Monday through Friday",
                        "Appointment will not be added", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Timestamp createdDateToAdd = Timestamp.valueOf(LocalDateTime.now());
            String createdByToAdd = users.getUserName();
            Timestamp lastUpdateToAdd = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdatedByToAdd = users.getUserName();
            int customerIDToAdd = Integer.parseInt(appointmentCustomerIDTextField.getText());

            ObservableList<appointments> allAppointments = appointments.getAllAppointments();
            for (appointments appointment : allAppointments) {
                LocalDateTime startTimesToCheck = appointment.getStart();
                LocalDateTime endTimesToCheck = appointment.getEnd();
                int customerIDsToCheck = appointment.getCustomerID();
                if (customerIDToAdd == customerIDsToCheck &&
                        (startLocalDateTimeToAdd.isEqual(startTimesToCheck) ||
                                startLocalDateTimeToAdd.isAfter(startTimesToCheck)) &&
                        (startLocalDateTimeToAdd.isEqual(endTimesToCheck) ||
                                startLocalDateTimeToAdd.isBefore(endTimesToCheck)) ||
                        customerIDToAdd == customerIDsToCheck &&
                                (endLocalDateTimeToAdd.isEqual(startTimesToCheck) ||
                                        endLocalDateTimeToAdd.isAfter(startTimesToCheck)) &&
                                (endLocalDateTimeToAdd.isEqual(endTimesToCheck) ||
                                        endLocalDateTimeToAdd.isBefore(endTimesToCheck))){
                    JOptionPane.showMessageDialog(null, "Appointment overlaps with previously scheduled" +
                                    "appointment for customer", "Error - Appointment will not be added",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            int userIDToAdd = Integer.parseInt(appointmentUserIDTextField.getText());
            String insertStatement = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, " +
                    "Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            jdbcQuery.setPreparedStatement(jdbc.getConnection(), insertStatement);
            PreparedStatement ps = jdbcQuery.getPreparedStatement();
            ps.setInt(1, appointmentIDToAdd);
            ps.setString(2, titleToAdd);
            ps.setString(3, descriptionToAdd);
            ps.setString(4, locationToAdd);
            ps.setString(5, typeToAdd);
            ps.setTimestamp(6, Timestamp.valueOf(startLocalDateTimeToAdd));
            ps.setTimestamp(7, Timestamp.valueOf(endLocalDateTimeToAdd));
            ps.setTimestamp(8, createdDateToAdd);
            ps.setString(9, createdByToAdd);
            ps.setTimestamp(10, lastUpdateToAdd);
            ps.setString(11, lastUpdatedByToAdd);
            ps.setInt(12, customerIDToAdd);
            ps.setInt(13, userIDToAdd);
            ps.setInt(14, contactIDToAdd);
            ps.execute();
            appointmentInfoToTableView();
            System.out.println("Appointment added");
            resetInputBoxes();
        }
        else JOptionPane.showMessageDialog(null, "Appointment information incomplete",
                "Appointment will not be added" , JOptionPane.ERROR_MESSAGE);
    }

    /**
     * updates existing appointment data
     * validates time and date
     * @throws SQLException
     */

    public void updateAppointmentInfo() throws SQLException {
        // verify that there is a matching foreign key for customer ID and user ID
        ObservableList<customers> allCustomerData = customers.getAllCustomers();
        ObservableList<Integer> allCustomerIDs = FXCollections.observableArrayList();
        for (customers customer : allCustomerData) {
            allCustomerIDs.add(customer.getCustomerID());
        }
        if (!allCustomerIDs.contains(Integer.parseInt(appointmentCustomerIDTextField.getText()))) {
            JOptionPane.showMessageDialog(null, "Please verify Customer Id",
                    "Appointment will not be added" , JOptionPane.ERROR_MESSAGE);
            return;
        }
        ObservableList<users> allUserData = users.getAllUsers();
        ObservableList<Integer> allUserIDs = FXCollections.observableArrayList();
        for (users user : allUserData) {
            allUserIDs.add(user.getUserID());
        }
        if (!allUserIDs.contains(Integer.parseInt(appointmentUserIDTextField.getText()))) {
            JOptionPane.showMessageDialog(null, "Please verify User Id",
                    "Appointment will not be added" , JOptionPane.ERROR_MESSAGE);
            return;
        }

        int appointmentIDToUpdate = Integer.parseInt(appointmentIDTextField.getText());
        String titleToUpdate = appointmentTitleTextField.getText();
        String descriptionToUpdate = appointmentDescriptionTextField.getText();
        String locationToUpdate = appointmentLocationTextField.getText();
        String typeToUpdate = appointmentTypeTextField.getText();
        String contactNameToUpdate = appointmentContactComboBox.getValue();
        int contactIDToUpdate = 0;
        ObservableList<contacts> contactsObservableList = contacts.getAllContacts();
        for (contacts contact : contactsObservableList) {
            if (contactNameToUpdate.equals(contact.getContactName())) {
                contactIDToUpdate = contact.getContactID();
            }
        }
        LocalDate startLocalDate = appointmentStartDatePicker.getValue();
        LocalDate endLocalDate = appointmentEndDatePicker.getValue();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startLocalTime = LocalTime.parse(appointmentStartTimeComboBox.getValue(), timeFormatter);
        LocalTime endLocalTime = LocalTime.parse(appointmentEndTimeComboBox.getValue(), timeFormatter);
        LocalDateTime startLocalDateTimeToUpdate = LocalDateTime.of(startLocalDate, startLocalTime);
        LocalDateTime endLocalDateTimeToUpdate = LocalDateTime.of(endLocalDate, endLocalTime);

        ZonedDateTime startLDTToZDT = ZonedDateTime.of(startLocalDateTimeToUpdate, ZoneId.systemDefault());
        ZonedDateTime startZDTToZDTEST = startLDTToZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalTime startAppointmentTimeToCheck = startZDTToZDTEST.toLocalTime();
        DayOfWeek startAppointmentDayToCheck = startZDTToZDTEST.toLocalDate().getDayOfWeek();
        int startAppointmentDayToCheckInt = startAppointmentDayToCheck.getValue();

        ZonedDateTime endLDTToZDT = ZonedDateTime.of(endLocalDateTimeToUpdate, ZoneId.systemDefault());
        ZonedDateTime endZDTToZDTEST = endLDTToZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalTime endAppointmentTimeToCheck = endZDTToZDTEST.toLocalTime();
        DayOfWeek endAppointmentDayToCheck = endZDTToZDTEST.toLocalDate().getDayOfWeek();
        int endAppointmentDayToCheckInt = endAppointmentDayToCheck.getValue();
        // business operation hours/days
        LocalTime startOfBusinessHours = LocalTime.of(8, 0, 0);
        LocalTime endOfBusinessHours = LocalTime.of(22, 0, 0);
        int startOfWeekInt = DayOfWeek.MONDAY.getValue();
        int endOfWeekInt = DayOfWeek.FRIDAY.getValue();
        // time and day checks
        if (startAppointmentTimeToCheck.isBefore(startOfBusinessHours) ||
                startAppointmentTimeToCheck.isAfter(endOfBusinessHours) ||
                endAppointmentTimeToCheck.isBefore(startOfBusinessHours) ||
                endAppointmentTimeToCheck.isAfter(endOfBusinessHours) ||
            startAppointmentDayToCheckInt < startOfWeekInt ||
                startAppointmentDayToCheckInt > endOfWeekInt ||
                endAppointmentDayToCheckInt < startOfWeekInt ||
                endAppointmentDayToCheckInt > endOfWeekInt) {
            JOptionPane.showMessageDialog(null, "Appointment is outside of business hours\n" +
                            "Please schedule between 08:00 and 22:00 EST Monday through Friday",
                    "Appointment will not be added", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Timestamp lastUpdateToUpdate = Timestamp.valueOf(LocalDateTime.now());
        String lastUpdatedByToUpdate = users.getUserName();
        int customerIDToUpdate = Integer.parseInt(appointmentCustomerIDTextField.getText());

        ObservableList<appointments> allAppointments = appointments.getAllAppointments();
        int selectedAppointmentID = 0;
        if (appTableMonthTabButton.isSelected()) {
            selectedAppointmentID = appMonthTable.getSelectionModel().getSelectedItem().getApptID();
        }
        if (appTableViewWeekTabButton.isSelected()) {
            selectedAppointmentID = appWeekTable.getSelectionModel().getSelectedItem().getApptID();

        }
        if (appTableViewAllTabButton.isSelected()) {
            selectedAppointmentID = appAllTable.getSelectionModel().getSelectedItem().getApptID();
        }
        for (appointments appointment : allAppointments) {
            LocalDateTime startTimesToCheck = appointment.getStart();
            LocalDateTime endTimesToCheck = appointment.getEnd();
            int customerIDsToCheck = appointment.getCustomerID();
            if ((appointment.getApptID() != selectedAppointmentID) &&
                    (customerIDToUpdate == customerIDsToCheck) &&
                    (startLocalDateTimeToUpdate.isEqual(startTimesToCheck) ||
                            startLocalDateTimeToUpdate.isAfter(startTimesToCheck)) &&
                    (startLocalDateTimeToUpdate.isEqual(endTimesToCheck) ||
                            startLocalDateTimeToUpdate.isBefore(endTimesToCheck)) ||
                (appointment.getApptID() != selectedAppointmentID) &&
                    (customerIDToUpdate == customerIDsToCheck) &&
                    (endLocalDateTimeToUpdate.isEqual(startTimesToCheck) ||
                            endLocalDateTimeToUpdate.isAfter(startTimesToCheck)) &&
                    (endLocalDateTimeToUpdate.isEqual(endTimesToCheck) ||
                            endLocalDateTimeToUpdate.isBefore(endTimesToCheck))) {
                JOptionPane.showMessageDialog(null, "Appointment overlaps with previously scheduled" +
                                "appointment for customer", "Error - Appointment will not be added",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        int userIDToUpdate = Integer.parseInt(appointmentUserIDTextField.getText());
        String updateStatement = "UPDATE appointments SET Appointment_ID = ?, Title = ?, Description = ?, " +
                "Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, " +
                "Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        jdbcQuery.setPreparedStatement(jdbc.getConnection(), updateStatement);
        PreparedStatement ps = jdbcQuery.getPreparedStatement();
        ps.setInt(1, appointmentIDToUpdate);
        ps.setString(2, titleToUpdate);
        ps.setString(3, descriptionToUpdate);
        ps.setString(4, locationToUpdate);
        ps.setString(5, typeToUpdate);
        ps.setTimestamp(6, Timestamp.valueOf(startLocalDateTimeToUpdate));
        ps.setTimestamp(7, Timestamp.valueOf(endLocalDateTimeToUpdate));
        ps.setTimestamp(8, lastUpdateToUpdate);
        ps.setString(9, lastUpdatedByToUpdate);
        ps.setInt(10, customerIDToUpdate);
        ps.setInt(11, userIDToUpdate);
        ps.setInt(12, contactIDToUpdate);
        ps.setInt(13, appointmentIDToUpdate);
        ps.execute();
        appointmentInfoToTableView();
        System.out.println("Appointment updated");
        resetInputBoxes();
    }

    /**
     *Populates text boxes with selected appointment info
     * Deletes selected appointment from database
     * @throws SQLException
     */
    public void deleteAppointmentInfo() throws SQLException {
        String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";
        jdbcQuery.setPreparedStatement(jdbc.getConnection(), deleteStatement);
        PreparedStatement ps = jdbcQuery.getPreparedStatement();
        int appointmentIDToDelete = 0;
        String appointmentType = "";
        if (appTableMonthTabButton.isSelected()) {
            appointmentIDToDelete = appMonthTable.getSelectionModel().getSelectedItem().getApptID();
            appointmentType = appMonthTable.getSelectionModel().getSelectedItem().getApptType();
        }
        if (appTableViewWeekTabButton.isSelected()) {
            appointmentIDToDelete = appWeekTable.getSelectionModel().getSelectedItem().getApptID();
            appointmentType = appWeekTable.getSelectionModel().getSelectedItem().getApptType();
        }
        if (appTableViewAllTabButton.isSelected()) {
            appointmentIDToDelete = appAllTable.getSelectionModel().getSelectedItem().getApptID();
            appointmentType = appAllTable.getSelectionModel().getSelectedItem().getApptType();
        }
        ps.setInt(1, appointmentIDToDelete);
        ps.execute();
        appointmentInfoToTableView();
        System.out.println("Appointment deleted");
        JOptionPane.showMessageDialog(null,
                "\nAppointment_ID: " + appointmentIDToDelete +
                        "\nType of Appointment: " + appointmentType,
                "Appointment will be cancelled", JOptionPane.INFORMATION_MESSAGE);
        resetInputBoxes();
    }

    public void openMainScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/mainScreen.fxml")));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root, 950, 700));
        customerScreen login = new customerScreen();
    }


    public void openCustomerScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/customerScreen.fxml")));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root, 950, 700));
        customerScreen login = new customerScreen();
    }
    public void openReportScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/reportScreen.fxml")));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root, 950, 700));
        customerScreen login = new customerScreen();
    }

    /**
     * Creates Pop Up windows to assist with full out appointment text fields
     * Utilizes lambda 2
     * Lambda 2 simplifies programing buttons that react to activation
     * -this increases the user experience and ease of use.
     * @throws IOException
     */

    public void popupActivation() {
        Function<String, String> fn = null;
        if (clickedButton == 1) { customerListPopUp.setText("Customer List OPEN"); customerListPopUp.setStyle("-fx-background-color: #CD5C5C"); userListPopUp.setText("User List"); userListPopUp.setStyle("-fx-background-color: #F08080");
            contactListPopUp.setText("Contact List"); contactListPopUp.setStyle("-fx-background-color: #F08080");}
        if (clickedButton == 2) { userListPopUp.setText("User List OPEN"); userListPopUp.setStyle("-fx-background-color: #CD5C5C"); customerListPopUp.setText("Customer List"); customerListPopUp.setStyle("-fx-background-color: #F08080"); contactListPopUp.setText("Contact List"); contactListPopUp.setStyle("-fx-background-color: #F08080");}
        if (clickedButton == 3) {
            contactListPopUp.setText("Contact List OPEN"); contactListPopUp.setStyle("-fx-background-color: #CD5C5C"); customerListPopUp.setText("Customer List"); customerListPopUp.setStyle("-fx-background-color: #F08080");
            userListPopUp.setText("User List"); userListPopUp.setStyle("-fx-background-color: #F08080");}
        clickedButton = -1;
    }

    public void newAction() {
        customerListPopUp.setOnAction(e -> { clickedButton = 1;try {openCustomerList();
        } catch (IOException throwables) { throwables.printStackTrace();}});
        userListPopUp.setOnAction(e -> { clickedButton = 2;try {openUserList();
        } catch (IOException throwables) { throwables.printStackTrace();}});
        contactListPopUp.setOnAction(e -> { clickedButton = 3;try {openContactList();
        } catch (IOException throwables) { throwables.printStackTrace();}});
    }

    public void openCustomerList() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/View/customerPopup.fxml")));
        Parent root1= fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Customer List");
        stage.setScene(new Scene(root1));
        stage.show();
        popupActivation();
    }

    public void openUserList() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/View/userPopup.fxml")));
        Parent root2 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("User List");
        stage.setScene(new Scene(root2));
        stage.show();
        popupActivation();
    }

    public void openContactList() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/View/contactPopup.fxml")));
        Parent root3 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Contact List");
        stage.setScene(new Scene(root3));
        stage.show();
        popupActivation();
    }
}
