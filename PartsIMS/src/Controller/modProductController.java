package Controller;

/** Controller "modProductController"
 * MVC to to develop the GUI application scene.
 * @author Jason M. Sanborn
 */

import Model.inventory;
import Model.part;
import Model.product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class modProductController implements Initializable{

    public TextField productId;
    public TextField productName;
    public TextField productInv;
    public TextField productPrice;
    public TextField productMin;
    public TextField productMax;

    public TableView partsTable;
    public TableColumn partId;
    public TableColumn partName;
    public TableColumn partInventoryLevel;
    public TableColumn partPrice;

    public TableView associatedPartsTable;
    public TableColumn associatedPartId;
    public TableColumn associatedPartName;
    public TableColumn associatedPartInv;
    public TableColumn associatedPartPrice;

    public TextField partSearch;
    public Button addAssociatedPart;
    public Button deleteAssociatedPart;
    public Button cancelProduct;
    public Button saveProduct;

    static public product selectedProduct;

    public void onAddAssociatedPart() {
        if (partsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection made");
            alert.setHeaderText("Please select a part");
            alert.showAndWait();
        }

        else {
            part selectedItem = (part) partsTable.getSelectionModel().getSelectedItem();
            product.addAssociatedPart(selectedItem);
        }

    }

    public void onDeleteAssociatedPart() {
        part selectedItem = (part) associatedPartsTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove part from product?");
        alert.setHeaderText("Remove part: " + selectedItem.getName() + "?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            associatedPartsTable.getItems().remove(selectedItem);
            product.deleteAssociatedPart(selectedItem);
        }
        else {
            alert.close();
        }
    }

    public void onSaveProduct(ActionEvent actionEvent) throws IOException, NumberFormatException{
        if (productName.getText().isEmpty()
                || productInv.getText().isEmpty()
                || productMin.getText().isEmpty()
                || productMax.getText().isEmpty()
                || productPrice.getText().isEmpty()) {

            System.out.println("Incomplete Input / Input Null");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Please verify all field have been completed.");
            alert.showAndWait();
        }

        else if (!inventory.isNumeric(productMax.getText())
                || !inventory.isNumeric(productMin.getText())
                || !inventory.isNumeric(productPrice.getText())
                || !inventory.isNumeric(productInv.getText())) {


            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incorrect Entry");
            alert.setHeaderText("Please verify Price, Inventory (min/max) are numeric.");
            alert.showAndWait();

        }


        else if (Integer.parseInt(productMin.getText()) > Integer.parseInt(productMax.getText())) {
            System.out.println("Input Error");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inventory Error");
            alert.setHeaderText("Product Inventory cannot exceed maximum set inventory. ");
            alert.showAndWait();
        }
        else if (Integer.parseInt(productMin.getText()) > Integer.parseInt(productInv.getText()) || Integer.parseInt(productInv.getText()) > Integer.parseInt(productMax.getText())) {
            System.out.println("Input Error");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inventory Error");
            alert.setHeaderText("Product inventory must be between minimum and maximum values.");
            alert.showAndWait();
        }

        else {
            System.out.println("Input Verified");
            try {
                int id = Integer.parseInt(productId.getText());
                String name = productName.getText();
                int invText = Integer.parseInt(productInv.getText());
                double price = Double.parseDouble(productPrice.getText());
                int min = Integer.parseInt(productMin.getText());
                int max = Integer.parseInt(productMax.getText());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Add Product");
                alert.setHeaderText("Would like to save this Product to the inventory? ");
                alert.showAndWait();


                if (alert.getResult() == ButtonType.OK)  {

                    product updateProduct = new product(id, name, price, invText, min, max, associatedPartsTable.getItems());
                    System.out.println(updateProduct);
                    inventory.updateProduct(updateProduct);
                    inventory.deleteProduct(selectedProduct);

                    Parent root = FXMLLoader.load(getClass().getResource("/View/main.fxml"));
                    Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root, 1100, 525);
                    stage.setTitle("Inventory Management System");
                    stage.setScene(scene);
                    stage.show();
                }
                else {
                    alert.close();
                }
            }
            catch (IOException E) {
                System.out.println(E.getLocalizedMessage());
            }
            catch ( NumberFormatException E) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText("Please format your input as follows:" +
                        "\nName: Alpha Numeric" +
                        "\nPrice, Inventory (min/max): Numerical");
                alert.showAndWait();
            }


        }

    }

    public void onCancelProduct(ActionEvent actionEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Product Modification?");
        alert.setHeaderText("Would you like to cancel input?");
        alert.setContentText("Press OK to return to main screen. \nPress Cancel to continue modification.");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {

            Parent root = FXMLLoader.load(getClass().getResource("/View/main.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1100, 525);
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();
        } else {
            alert.close();
        }
    }

    public void onProductPartSearch() {
        if (partSearch.getText().trim().isEmpty()) {
            partsTable.setItems(inventory.getAllParts());
        } else {
            try {
                part returnedPart = inventory.lookupPart(Integer.parseInt(partSearch.getText()));

                if (returnedPart == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Part not found");
                    alert.setHeaderText("Please verify search information");
                    alert.showAndWait();
                } else {
                    ObservableList<part> filteredPartsList = FXCollections.observableArrayList();
                    filteredPartsList.add(returnedPart);
                    partsTable.setItems(filteredPartsList);
                }

            } catch (NumberFormatException e) {
                System.out.println("Number Format Exception");
                partsTable.setItems(inventory.lookupPart(partSearch.getText().trim()));
            }
        }
    }

    public void updatePartsTable() {
        partsTable.setItems(inventory.getAllParts());
    }

    public void updateAssociatedPartsTable() {
        associatedPartsTable.setItems(product.getAllAssociatedParts());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productId.setText(String.valueOf(selectedProduct.getId()));
        productName.setText(selectedProduct.getName());
        productInv.setText(String.valueOf(selectedProduct.getStock()));
        productPrice.setText(String.valueOf((selectedProduct.getPrice())));
        productMax.setText(String.valueOf(selectedProduct.getMax()));
        productMin.setText(String.valueOf(selectedProduct.getMin()));

        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        updatePartsTable();

        associatedPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        updateAssociatedPartsTable();
    }

}
