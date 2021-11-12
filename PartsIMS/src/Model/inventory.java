package Model;

/** Class "Inventory"
 * Hold the observable lists of all parts and products.
 * Defines methods for use with object instances.
 * @author Jason M. Sanborn
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class inventory {
    /**
     * Observable list for part class.
     */
    public static final ObservableList<part> allParts = FXCollections.observableArrayList();

    /**
     * Observable list for product class.
     */
    public static ObservableList<product> allProducts = FXCollections.observableArrayList();

    /**
     @param newPart -Adds a new instance of the parts class to the allParts list
     */
    public static void addPart(part newPart) {
        allParts.add(newPart);
    }


    /**
     @param newProduct -Adds a new instance of the products class to the allProducts list
     */
    public static void addProduct(product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     @param partID numeric part identifier
     @return -returns a part from the allParts list
     */
    public static part lookupPart(int partID) {
        for(part part : allParts) {
            if (part.getId() == partID) {
                return part;
            }
        }
        return null;
    }

    /**
     @param productID numeric part identifier
     @return -returns a product from the allProducts list
     */
    public static product lookupProduct(int productID){
        for(product prod  : allProducts) {
            if (prod.getId() == productID) {
                return prod;
            }
        }
        return null;
    }

    /**
     @param partName part name
     @return -Returns part request by part name.
     */
    public static ObservableList<part> lookupPart(String partName){
        ObservableList<part> filteredPartsList = FXCollections.observableArrayList();
        for (part p : allParts) {
            if (partName.compareTo(p.getName()) == 0) {
                filteredPartsList.add(p);
            }
        }
        return filteredPartsList;

    }

    /**
     @param productName product name
     @return -returns product requested bu product name.
     */
    public static ObservableList<product> lookupProduct(String productName) {
        ObservableList<product> filteredProductList = FXCollections.observableArrayList();
        for (product p : allProducts) {
            if (productName.compareTo(p.getName()) == 0) {
                filteredProductList.add(p);
            }
        }
        return filteredProductList;
    }

    /** Updates part within list
     * @param part
     */
    public static void updatePart(part part) {allParts.add(part);}

    public static void updateProduct(product product) {allProducts.add(product);}

    /** Removes part from list
     @param selectedPart -part marked for removal
     @return -statement returns true if item was delete else false.
     */
    public static boolean deletePart(part selectedPart) {
        int id = selectedPart.getId();
        part lookupPart = lookupPart(id);

        return allParts.remove(lookupPart);
    }

    /** Removes a product from list
     @param selectedProduct -Product marked for removal
     @return -statement returns true if item was delete else false.
     */
    public static boolean deleteProduct(product selectedProduct) {
        int id = selectedProduct.getId();
        product lookupProduct = lookupProduct(id);
        return allProducts.remove(lookupProduct);
    }

    /**
     * @return -returns all parts currently in list via get method.
     */
    public static ObservableList<part> getAllParts() {
        return allParts;
    }

    /**
     @return -returns all products currently in list via get method.
     */
    public static ObservableList<product> getAllProducts() {
        return allProducts;
    }

    /**
     @param strNum -string to be checked for numeric entry
     @return - verifies that string entry is numeric by producing a boolean response.
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}



