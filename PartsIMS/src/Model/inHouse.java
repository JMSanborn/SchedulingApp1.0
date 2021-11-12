package Model;

/** Class "inHouse"
 * extends abstract class "part". Allows for different input properties on addPart scene.
 * Defines methods for use with object instances.
 * @author Jason M. Sanborn
 */

public class inHouse extends part{

    private int machineID;

    /** Constructor to define the parameters of inHouse class.  Includes alternate machineId parameter for parts marked as being made in house.
     @param id system defined
     @param name string name of the product
     @param price price in USD
     @param stock amount held in inventory
     @param min minimum inventory
     @param max maximum inventory
     @param machineId name of firm subcontracted to build the part
     */

    public inHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineID = machineId;
    }

    /**
     *  @param machineID - declares the machineId to set
     */
    public void setMachineId(int machineID) {
        this.machineID = machineID;
    }

    /** Get Machine ID
     * @return -returns the machineID
     **/
    public int getMachineId() {
        return machineID;
    }
}

