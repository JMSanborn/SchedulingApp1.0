package Model;

import Database.jdbc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class "firstLevelDivisions"
 ** Defines methods for use with object instances.
 * @author Jason M. Sanborn
 */

public class firstLevelDivisions {
    private int divisionID;
    private String divisionName;
    public int country_ID;

    /** Constructor to define the parameters of the firstLevelDivisions class.
     * @param divisionID the division ID to set
     * @param divisionName the division name to set
     * @param country_ID the country ID to set
     */

    public firstLevelDivisions(int divisionID, String divisionName, int country_ID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.country_ID = country_ID;
    }

    /**
     *
     * @return -returns the division ID
     */
    public int getDivisionID() {return divisionID;}

    /**
     *
     * @return -returns the division name
     */
    public String getDivisionName() {return divisionName;}

    /**
     *
     * @return -returns the country ID
     */
    public int getCountry_ID() {return country_ID;}

    /**
     *
     * @return -returns observable list of all first-level division
     * @throws SQLException
     */
    public static ObservableList<firstLevelDivisions> getAllFirstLevelDivisions() throws SQLException {
        ObservableList<firstLevelDivisions> firstLevelDivisionsObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * from first_level_divisions";
        PreparedStatement ps = jdbc.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int country_ID = rs.getInt("COUNTRY_ID");
            firstLevelDivisions firstLevelDivision = new firstLevelDivisions(divisionID, divisionName, country_ID);
            firstLevelDivisionsObservableList.add(firstLevelDivision);
        }
        return firstLevelDivisionsObservableList;
    }
}

