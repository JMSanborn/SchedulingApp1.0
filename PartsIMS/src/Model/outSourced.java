package Model;

/** Class "outSourced"
 * extends abstract class "part". Allows for different input properties on addPart scene.
 * Defines methods for use with object instances.
 * @author Jason M. Sanborn
 */

public class outSourced extends part {
    private String companyName;

    /** Constructor to define the parameters of class Outsource.  Includes alternate company name parameter for parts marked as being outsourced.
     @param id system defined
     @param name string name of the product
     @param price price in USD
     @param stock amount held in inventory
     @param min minimum inventory
     @param max maximum inventory
     @param companyName name of firm subcontracted to build the part
     */

    public outSourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     @param companyName -declares the companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     @return -returns the companyName
     */
    public String getCompanyName() {
        return companyName;
    }
}
