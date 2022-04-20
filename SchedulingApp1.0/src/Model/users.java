package Model;

import Database.jdbc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class "Part"
 ** Defines methods for use with object instances.
 * @author Jason M. Sanborn
 */

public class users {

    public static String userName;
    private Integer userID;
    public String userPassword;

    /** Constructor to define the parameters of the Parts class.
     @param userName username
     @param userID user id number
     @param userPassword user password
     */

    public users(Integer userID, String userName, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     * @return -returns the id
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * @return -returns the username
     */
    public static String getUserName() {
        return userName;
    }

    /**
     * @return -returns the user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @return -returns observable list of all users
     * @throws SQLException
     */
    public static ObservableList<users> getAllUsers() throws SQLException {
        ObservableList<users> usersObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * from users";
        PreparedStatement ps = jdbc.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String userPassword = rs.getString("Password");
            users user = new users(userID, userName, userPassword);
            usersObservableList.add(user);
        }
        return usersObservableList;
    }
}


