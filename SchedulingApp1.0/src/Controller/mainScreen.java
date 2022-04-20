package Controller;

/** Controller "mainController" -Main Scene
 * MVC to to develop the GUI application scene.
 * @author Jason M. Sanborn
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Objects;

public class mainScreen {


    @FXML public TableView<customers> customerTable;
    @FXML public TableColumn<customers, Integer> customerTableIDCol;
    @FXML public TableColumn<customers, String> customerTableNameCol;
    @FXML public TableColumn<customers, String> customerTableAddressCol;
    @FXML public TableColumn<customers, Integer> customerTableFirstLevelDivisionCol;
    @FXML public TableColumn<customers, String> customerTablePostalCodeCol;
    @FXML public TableColumn<customers, String> customerTablePhoneCol;


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
    @FXML public TableColumn <appointments, Integer>appMonthTableUserIDCol;


    @FXML public Tab appTableWeekTabButton;
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
    @FXML public TableColumn <appointments, Integer>appWeekTableUserIDCol;


    @FXML public Tab appTableAllTabButton;
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
    @FXML public TableColumn <appointments, Integer>appAllTableUserIDCol;



    public void initialize() throws SQLException {
        customerTableIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerTableNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTableAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerTableFirstLevelDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        customerTablePostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        customerTablePhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
        customerInfoToTableView();

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

        ObservableList<contacts> contactsObservableList = contacts.getAllContacts();
        ObservableList<String> allContactsNames = FXCollections.observableArrayList();
        contactsObservableList.forEach(contacts -> allContactsNames.add(contacts.getContactName()));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        ObservableList<String> appointmentTimesDuringDay = FXCollections.observableArrayList();
        LocalTime firstAppointmentTimeLDT = LocalTime.MIN.plusHours(8);
        LocalTime lastAppointmentTimeLDT = LocalTime.MAX.minusHours(1).minusMinutes(59);
        while (firstAppointmentTimeLDT.isBefore(lastAppointmentTimeLDT)) {
            appointmentTimesDuringDay.add(dateTimeFormatter.format(firstAppointmentTimeLDT));
            firstAppointmentTimeLDT = firstAppointmentTimeLDT.plusMinutes(30);
        }
        appointmentInfoToTableView();
    }

    public void customerInfoToTableView() throws SQLException {
        ObservableList<customers> allCustomersList = customers.getAllCustomers();
        customerTable.setItems(allCustomersList);
    }

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
            if (appointment.getStart().isAfter(currentWeekStart) && appointment.getStart().isBefore(currentWeekEnd)) {
                appointmentsWeek.add(appointment);
            }
        }

        appMonthTable.setItems(appointmentsMonth);
        appWeekTable.setItems(appointmentsWeek);
        appAllTable.setItems(allAppointmentsList);
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

    public void openReportScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/reportScreen.fxml")));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root, 950, 700));
    }
}
