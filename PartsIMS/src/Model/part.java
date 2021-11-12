package Model;

/** Class "Part"
 ** Defines methods for use with object instances.
 * @author Jason M. Sanborn
 */

public abstract class part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** Constructor to define the parameters of the Parts class.
     @param id identification number
     @param name product name
     @param price cost of product
     @param stock current inventory count
     @param min minimum inventory count
     @param max maximum inventory count
     */

    public part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
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
}

