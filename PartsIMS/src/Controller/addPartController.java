package Controller;

/** Controller "addPartController"
 * MVC to to develop the GUI application scene.
 * @author Jason M. Sanborn
 * RUNTIME ERROR - Index out of bounds exception was created when ever a new part was created into an empty list.
 * This was corrected by checking to see if this list was empty before asigning a part number vis a boolean responce.
 * If list was empty program was give a number to start list if not the list continues sequentally.
 */

import Model.inventory;
import Model.part;
import Model.inHouse;
import Model.outSourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addPartController implements Initializable {

    public RadioButton inHouseRadio;
    public RadioButton outSourcedRadio;
    public TextField textId;
    public TextField textName;
    public TextField textInv;
    public TextField textPrice;
    public TextField textMax;
    public TextField textMin;
    public Button buttonSave;
    public Button buttonCancel;
    public ToggleGroup tGroupSource;
    public Label labelMulti;
    public TextField textMulti;


    /**Updates add Part Scene based on Radio Button selection. Changes Machine/Company text field label*/

    public void onSourceRadio() {
        ToggleGroup tGroupSource = new ToggleGroup();
        inHouseRadio.setToggleGroup(tGroupSource);
        outSourcedRadio.setToggleGroup(tGroupSource);

        if (inHouseRadio.isSelected()) {
            labelMulti.setText("Machine ID");
        } else if (outSourcedRadio.isSelected()) {
            labelMulti.setText("Company Name");
        }

    }

    public void onButtonCancel(ActionEvent actionEvent) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/View/main.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1100, 525);
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();
    }

    public void addPartButton(ActionEvent actionEvent) throws Exception, NumberFormatException {
        if (textName.getText().isEmpty()
                || textInv.getText().isEmpty()
                || textMulti.getText().isEmpty()
                || textMin.getText().isEmpty()
                || textMax.getText().isEmpty()
                || textPrice.getText().isEmpty()) {

            System.out.println("Incomplete Input / Input Null");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText("Please verify all field have been completed.");
            alert.showAndWait();
        }

        else if (!inventory.isNumeric(textMax.getText())
                || !inventory.isNumeric(textMin.getText())
                || !inventory.isNumeric(textPrice.getText())
                || !inventory.isNumeric(textInv.getText())) {


            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incorrect Entry");
            alert.setHeaderText("Please verify Price, Inventory (min/max) are numeric.");
            alert.showAndWait();

        }

        else if (Integer.parseInt(textMin.getText()) > Integer.parseInt(textMax.getText())) {
            System.out.println("Input Error");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inventory Error");
            alert.setHeaderText("Part inventory cannot exceed maximum set inventory. ");
            alert.showAndWait();
        }

        else if (Integer.parseInt(textMin.getText()) > Integer.parseInt(textInv.getText()) || Integer.parseInt(textInv.getText()) > Integer.parseInt(textMax.getText())) {
            System.out.println("Input Error");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inventory Error");
            alert.setHeaderText("Part inventory must be between minimum and maximum values.");
            alert.showAndWait();
        }


        else {

            System.out.println(!inventory.isNumeric(textInv.getText()));

            System.out.println("Input Verified");
            try {
                int id = Integer.parseInt(textId.getText());
                String name = textName.getText();
                int invText = Integer.parseInt(textInv.getText());
                double price = Double.parseDouble(textPrice.getText());
                int min = Integer.parseInt(textMin.getText());
                int max = Integer.parseInt(textMax.getText());
                String inputSource = textMulti.getText();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Add Part");
                alert.setHeaderText("Would like to save this part to the inventory? ");
                alert.showAndWait();


                if (alert.getResult() == ButtonType.OK)  {


                    if (inHouseRadio.isSelected()) {
                        inHouse inhousePart = new inHouse(id, name, price, invText, min, max, Integer.parseInt(inputSource));
                        inhousePart.setMachineId(Integer.parseInt(inputSource));
                        System.out.println(inhousePart.getMachineId());
                        inventory.addPart(inhousePart);

                    }
                    else if (outSourcedRadio.isSelected()) {
                        System.out.println("Saving Outsourced");
                        outSourced outsourcedPart = new outSourced(id, name, price, invText, min, max, inputSource);
                        outsourcedPart.setCompanyName(inputSource);
                        System.out.println(outsourcedPart.getCompanyName());
                        inventory.addPart(outsourcedPart);
                    }


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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText(E.getLocalizedMessage());
                alert.showAndWait();
            }
            catch (NumberFormatException E) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Type Error");
                alert.setHeaderText("Please format your input as follows:" +
                        "\nName, Company Name: Alpha Numeric" +
                        "\nPrice, Inventory (min/max), Machine Id: Numerical");
                alert.showAndWait();
            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inHouseRadio.setSelected(true);

        boolean partList = inventory.allParts.isEmpty();
        if (partList == true)
            textId.setText(String.valueOf(1));
        else {
            part lastId = inventory.getAllParts().get(inventory.getAllParts().size() - 1);
            int newId = lastId.getId();
            textId.setText(String.valueOf(++newId));
        }

    }
}