package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Class "Product"
 * Defines methods for use with object instances.
 * @author Jason M. Sanborn
 */

public class product {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    public static ObservableList<part> associatedParts = FXCollections.observableArrayList();

    /** Constructor to define the parameters of the product class.
     @param id identification number
     @param name product name
     @param price cost of product
     @param stock current inventory count
     @param min minimum inventory count
     @param max maximum inventory count
     @param associatedParts parts included with product
     */

    public product(int id, String name, double price, int stock, int min, int max, ObservableList<part> associatedParts) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = associatedParts;
    }

    /** adds the selected part to the products part list
     */
    public static void addAssociatedPart(part part) {
        associatedParts.add(part);
    }

    /** removes the selected part from the products part list.
     * @return  -Verifies deletion was complied with iva boolean value.
     */

    public static boolean deleteAssociatedPart(part part){
        int id = part.getId();
        boolean isRemoved = false;
        for(int i = 0; i < associatedParts.size(); i++) {
            if (associatedParts.get(i).getId() == id) {
                associatedParts.remove(i);
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    /**
     * @return -returns the id
     */
    public int getId() {return id;}

    /**
     * @param id -declares the id to set
     */
    public void setId(int id) {this.id = id;}

    /**
     * @return -returns the name
     */
    public String getName() {return name;}

    /**
     * @param name -declares the name to set
     */
    public void setName(String name) {this.name = name;}

    /**
     * @return -returns the price
     */
    public double getPrice() {return price;}

    /**
     * @param price -declares the price to set
     */
    public void setPrice(double price) {this.price = price;}

    /**
     * @return -returns inventory count
     */
    public int getStock() {return stock;}

    /**
     * @param stock - declares the inventory count to set
     */
    public void setStock(int stock) {this.stock = stock;}

    /**
     * @return -returns the minimum inventory allowed
     */
    public int getMin() {return min;}

    /**
     * @param min -declares the minimum inventory to set
     */
    public void setMin(int min) {this.min = min;}

    /**
     * @return -returns the maximum inventory allowed
     */
    public int getMax() {return max;}

    /**
     * @param max -declares the maximum inventory to set
     */
    public void setMax(int max) {this.max = max;}

    /**
     *@return -returns all associated parts
     */
    public static ObservableList<part> getAllAssociatedParts() {
        return associatedParts;
    }

}
