package Model;

import java.util.ArrayList;

public class CustomerReportFirstLevelDivision {

    public String firstLevelDivision;
    public ArrayList<String> countryList;

    /**
     *
     * @param firstLevelDivision the first-level division to set
     * @param countryList the country list to set
     */
    public CustomerReportFirstLevelDivision(String firstLevelDivision, ArrayList<String> countryList) {
        this.firstLevelDivision = firstLevelDivision;
        this.countryList = countryList;
    }

    /**
     *
     * @return the first-level division
     */
    public String getFirstLevelDivision() {
        return firstLevelDivision;
    }

    /**
     *
     * @return the customer list
     */
    public ArrayList<String> getCountryList() {
        return countryList;
    }

}