package Model;

import Database.jdbc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class "contacts"
 * Defines methods for use with object instances.
 * @author Jason M. Sanborn
 */

public class contacts {
    public int contactID;
    public String contactName;
    public String contactEmail;

    /** Constructor to define the parameters of the contacts class.
     * @param contactID the contact ID to set
     * @param contactName the contact name to set
     * @param contactEmail the contact email to set
     */
    public contacts(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     * @return -returns the contact ID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * @return -returns the contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @return -returns the contact email
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * @return -returns observable list of all contacts
     * @throws SQLException
     */
    public static ObservableList<contacts> getAllContacts() throws SQLException {
        ObservableList<contacts> contactsObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * from contacts";
        PreparedStatement ps = jdbc.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String contactEmail = rs.getString("Email");
            contacts contact = new contacts(contactID, contactName, contactEmail);
            contactsObservableList.add(contact);
        }
        return contactsObservableList;
    }
}
