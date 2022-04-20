package Model;

/** Class "countries"
 * Defines methods for use with object instances.
 * @author Jason M. Sanborn
 */

import Database.jdbc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class countries {
    private int countryID;
    private String countryName;

    /** Constructor to define the parameters of the countries class.
     @param countryID the country ID to set
     @param countryName the country name to set
     */

    public countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**
     *
     *@return -return the country ID
     */
    public int getCountryID() {return countryID;}

    /**
     *@return -return the country name
     */
    public String getCountryName() {return countryName;}

    /**
     * @return -return observable list of all country data from database
     * @throws SQLException
     */
    public static ObservableList<countries> getAllCountries() throws SQLException {
        ObservableList<countries> countriesObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * from countries";
        PreparedStatement ps = jdbc.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            countries country = new countries(countryID, countryName);
            countriesObservableList.add(country);
        }
        return countriesObservableList;
    }
}

