package Model;

import Database.jdbc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class "customers"
 * Defines methods for use with object instances.
 * @author Jason M. Sanborn
 */

public class customers {
    private int customerID;
    private String customerName;
    private String customerPhoneNumber;
    private String customerAddress;
    private String customerPostalCode;
    public int divisionID;

    /** Constructor to define the parameters of the Customers Class.
     @param customerID Customer ID
     @param customerName Customer Name
     @param customerPhoneNumber Customer Phone Number
     @param customerAddress Customer Address
     @param customerPostalCode Customer Postal Code
     @param divisionID First level Division ID
     */

    public customers(int customerID, String customerName, String customerAddress, String customerPostalCode,
                     String customerPhoneNumber, int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhoneNumber = customerPhoneNumber;
        this.divisionID = divisionID;
    }

    /**
     * @return -returns the customer ID
     */
    public int getCustomerID() {return customerID;}

    /**
     * @return -returns the customer name
     */
    public String getCustomerName() {return customerName;}

    /**
     * @return -returns the customer address
     */
    public String getCustomerAddress() {return customerAddress;}

    /**
     * @return -returns the customer postal code
     */
    public String getCustomerPostalCode() {return customerPostalCode;}

    /**
     * @return -returns the customer phone number
     */
    public String getCustomerPhoneNumber() {return customerPhoneNumber;}

    /**
     * @return -returns the division ID
     */
    public int getDivisionID () {return divisionID;}

    /**
     * @return -returns observable list of all customers
     * @throws SQLException
     */
    public static ObservableList<customers> getAllCustomers() throws SQLException {
        ObservableList<customers> customersObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * from customers";
        PreparedStatement ps = jdbc.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerAddress = rs.getString("Address");
            String customerPostalCode = rs.getString("Postal_Code");
            String customerPhone = rs.getString("Phone");
            int divisionID = rs.getInt("Division_ID");
            customers customer = new customers(customerID, customerName, customerAddress, customerPostalCode, customerPhone, divisionID);
            customersObservableList.add(customer);
        }
        return customersObservableList;
    }
}