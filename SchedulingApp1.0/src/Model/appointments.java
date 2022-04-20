package Model;

import Database.jdbc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


/** Class "appointments"
 * Defines methods for use with object instances.
 * @author Jason M. Sanborn
 */

public class appointments {
    private static LocalDate startDate;
    private static LocalDate endDate;
    private int apptID;
    private String apptTitle;
    private String apptDescription;
    private String apptLocation;
    private String apptType;
    private LocalDateTime start;
    private LocalDateTime end;
    public int customerID;
    public int userID;
    public int contactID;

    /**
     * Constructor to define the parameters of the appointments class.
     * @param apptID appointment ID
     * @param apptTitle appointment title
     * @param apptDescription appointment description
     * @param apptLocation appointment location
     * @param apptType appointment type
     * @param start appointment start LocalDateTime
     * @param end appointment end LocalDateTime
     * @param customerID customer ID
     * @param userID user ID
     * @param contactID contact ID
     */
    public appointments(int apptID, String apptTitle, String apptDescription,
                        String apptLocation, String apptType, LocalDateTime start, LocalDateTime end, int customerID,
                        int userID, int contactID, LocalDate startDate, LocalDate endDate) {
        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptType = apptType;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public appointments() {

    }
    /** Gets Start Date
     * @return startDate LocalDate value of Appointment Start Dated
     * For future expansion
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /** Sets Appointment Start Date
     * @param startDate LocalDate value of Appointment Start Date
     * For future expansion
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /** Gets End Date
     * @return endDate LocalDate value of Appointment End Date
     * For future expansion
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /** Sets Appointment End Date
     * @param endDate LocalDate value of Appointment End Date
     * For future expansion
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    /**
     * @return -returns the appointmentID
     */
    public int getApptID() {
        return apptID;
    }

    /**
     * @return -returns the appointment title
     */
    public String getApptTitle() {
        return apptTitle;
    }

    /**
     * @return -returns the appointment description
     */
    public String getApptDescription() {
        return apptDescription;
    }

    /**
     * @return -returns the appointment location
     */
    public String getApptLocation() {
        return apptLocation;
    }

    /**
     * @return -returns the appointment type
     */
    public String getApptType() {
        return apptType;
    }

    /**
     * @return -returns the start LocalDateTime
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * @return -returns the end LocalDateTime
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * @return -returns the customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @return -returns the user ID
     */
    public int getUserID() {return userID;}

    /**
     * @return -returns the contact ID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Method for checking upcoming appointment on log in
     * Displays upcoming appointment message
     * @throws SQLException
     */
    public void appointmentCheck() throws SQLException {
       int currentUserID = 1;
        LocalDateTime timeOfLogInNow = LocalDateTime.now();
        LocalDateTime timeOfLogInBottomWindow = LocalDateTime.now().minusMinutes(15);
        LocalDateTime timeOfLogInTopWindow = LocalDateTime.now().plusMinutes(15);
        LocalDateTime validateStart;
        LocalDateTime validateEnd;
        int apptIDToShow = 0;
        LocalDateTime startToShow = null;
        LocalDateTime endToShow = null;
        boolean upcomingAppt = false;
        boolean currentAppt = false;
        ObservableList<appointments> appointmentsObservableList = appointments.getAllAppointments();
        for (appointments appointment : appointmentsObservableList) {
            validateStart = appointment.getStart();
            validateEnd = appointment.getEnd();
            int userIDsToCheck = appointment.getUserID();
            if ((userIDsToCheck == currentUserID) &&
                    (validateStart.isAfter(timeOfLogInBottomWindow) || validateStart.isEqual(timeOfLogInBottomWindow)) &&
                    (validateStart.isBefore(timeOfLogInTopWindow) || (validateStart.isEqual(timeOfLogInTopWindow)))) {
                apptIDToShow = appointment.getApptID();
                startToShow = validateStart;
                endToShow = validateEnd;
                upcomingAppt = true;
            } else if ((userIDsToCheck == currentUserID) &&
                    (timeOfLogInNow.isAfter(validateStart) || timeOfLogInNow.isEqual(validateStart)) &&
                    (timeOfLogInNow.isBefore(validateEnd) || (timeOfLogInNow.isEqual(validateEnd)))) {
                System.out.println(timeOfLogInNow);
                System.out.println(validateStart);
                System.out.println(validateEnd);
                apptIDToShow = appointment.getApptID();
                startToShow = validateStart;
                endToShow = validateEnd;
                currentAppt = true;
            }
        }
        if (upcomingAppt) {
            JOptionPane.showMessageDialog(null,
                    "Scheduled appointment within 15 minutes" +
                            "\nAppointment ID: " + apptIDToShow +
                            "\nStart Date: " + startToShow.toLocalDate() +
                            "\nEnd Date: " + endToShow.toLocalDate() +
                            "\nStart Time: " + startToShow.toLocalTime() +
                            "\nEnd Time: " + endToShow.toLocalTime(),
                    "Upcoming Appointment", JOptionPane.WARNING_MESSAGE);
        } else if (currentAppt) {
            JOptionPane.showMessageDialog(null,
                    "There is a current appointment" +
                            "\nAppointment ID: " + apptIDToShow +
                            "\nStart Date: " + startToShow.toLocalDate() +
                            "\nEnd Date: " + endToShow.toLocalDate() +
                            "\nStart Time: " + startToShow.toLocalTime() +
                            "\nEnd Time: " + endToShow.toLocalTime(),
                    "Current Appointment", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No scheduled appointments",
                    "Appointments", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * @return -returns all appointments from database
     * @return -time converted to local time
     * @throws SQLException if exception has occurred
     */
    public static ObservableList<appointments> getAllAppointments() throws SQLException {
        ObservableList<appointments> appointmentsObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * from appointments";
        PreparedStatement ps = jdbc.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            appointments appointment = new appointments(appointmentID, appointmentTitle, appointmentDescription,
                    appointmentLocation, appointmentType, start, end, customerID, userID, contactID, startDate, endDate);
            appointmentsObservableList.add(appointment);
        }
        return appointmentsObservableList;
    }
}
