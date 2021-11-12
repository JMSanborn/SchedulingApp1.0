package Controller;

/** Controller "mainController" -Main Scene
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


public class mainController implements Initializable {


    public Button partAdd;
    public Button exitButton;
    public Button partDelete;
    public TableView partsTable;
    public TableColumn partId;
    public TableColumn partName;
    public TableColumn partInv;
    public TableColumn partPrice;
    public TextField partSearch;
    public Button partModify;

    public TableView productsTable;
    public TableColumn productId;
    public TableColumn productName;
    public TableColumn productInv;
    public TableColumn productPrice;
    public TextField productSearch;
    public Button productDelete;
    public Button productModify;
    public Button productAdd;


    public void OnPartAdd(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/addPart.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Parts IMS");
        stage.setScene(scene);
        stage.show();
    }

    public void onPartModify(ActionEvent actionEvent) throws Exception {
        if (partsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection made");
            alert.setHeaderText("Please select a part");
            alert.showAndWait();

        } else {

            part selectedItem = (part) partsTable.getSelectionModel().getSelectedItem();
            System.out.println(selectedItem.getName());

            modPartController.selectedPart = selectedItem;

            Parent root = FXMLLoader.load(getClass().getResource("/View/modPart.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Modify Part");
            stage.setScene(scene);
            stage.show();

        }
    }

    public void onPartDelete() {
        if (partsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection made");
            alert.setHeaderText("Please select item to delete.");
            alert.showAndWait();

        } else {
            part selectedItem = (part) partsTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Part?");
            alert.setHeaderText("Delete " + selectedItem.getName() + "?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                inventory.deletePart(selectedItem);
                partsTable.getItems().remove(selectedItem);


            } else {
                alert.close();
            }

        }
    }

    public void onProductAdd(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/addProduct.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1100, 750);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
    public void onProductModify(ActionEvent actionEvent) throws Exception{
        if (productsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection made");
            alert.setHeaderText("Please select a product to modify");
            alert.showAndWait();

        } else {
            product selectedItem = (product) productsTable.getSelectionModel().getSelectedItem();
            System.out.println(selectedItem.getName());

            modProductController.selectedProduct = selectedItem;

            Parent root = FXMLLoader.load(getClass().getResource("/View/modProduct.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1100, 750);
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();
        }
    }
    public void onProductDelete() {
        if (productsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection made");
            alert.setHeaderText("Please select a product to delete");
            alert.showAndWait();

        }
        else {
            product selectedItem = (product) productsTable.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product?");
            alert.setHeaderText("Delete " + selectedItem.getName() + "?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK)  {

                if (!selectedItem.getAllAssociatedParts().isEmpty()) {
                    Alert newAlert = new Alert(Alert.AlertType.ERROR);
                    newAlert.setTitle("Delete Error");
                    newAlert.setHeaderText("All parts must be remove from Product before deletion");
                    newAlert.showAndWait();
                }
                else {
                    inventory.deleteProduct(selectedItem);
                    productsTable.getItems().remove(selectedItem);
                }

            }
            else {
                alert.close();
            }




        }
    }

    public void onExitButton(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onPartSearch() {
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

    public void onProductSearch() {
        if (productSearch.getText().trim().isEmpty()) {
            productsTable.setItems(inventory.getAllProducts());
        } else {
            try {
                product returnedProduct = inventory.lookupProduct(Integer.parseInt(productSearch.getText()));

                if (returnedProduct == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Product not found");
                    alert.setHeaderText("Please verify search information");
                    alert.showAndWait();
                } else {
                    ObservableList<product> filteredProductsList = FXCollections.observableArrayList();
                    filteredProductsList.add(returnedProduct);
                    productsTable.setItems(filteredProductsList);
                }

            } catch (NumberFormatException e) {
                System.out.println("Number Format Exception");
                productsTable.setItems(inventory.lookupPart(productSearch.getText().trim()));
            }


        }
    }

    public void updateParts() {
        partsTable.setItems(inventory.getAllParts());
    }

    public void updateProducts() {
        productsTable.setItems(inventory.getAllProducts());
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){

        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        updateParts();

        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        updateProducts();
    }
}
