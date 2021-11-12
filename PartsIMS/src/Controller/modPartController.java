package Controller;

/** Controller "modPartController"
 * MVC to to develop the GUI application scene.
 * @author Jason M. Sanborn
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

public class modPartController implements Initializable {


    public RadioButton inHouseRadio;
    public RadioButton outSourcedRadio;
    public TextField textId;
    public TextField textName;
    public TextField textInv;
    public TextField textPrice;
    public TextField textMax;
    public TextField textMin;
    public Button buttonMod;
    public Button buttonCancel;
    public ToggleGroup tGroupSource;
    public Label labelMulti;
    public TextField textMulti;

    public static part selectedPart;

    /** Updates the UI of based on Radio Buttons.
     Builds the ToggleGroup for RadioButtons, and changes the dualPurpLabel based on selection. */
    public void viewPart() {
        ToggleGroup tGroupSource = new ToggleGroup();
        inHouseRadio.setToggleGroup(tGroupSource);
        outSourcedRadio.setToggleGroup(tGroupSource);

        textId.setText(String.valueOf(selectedPart.getId()));
        textName.setText(selectedPart.getName());
        textInv.setText(String.valueOf(selectedPart.getStock()));
        textPrice.setText(String.valueOf(selectedPart.getPrice()));
        textMax.setText(String.valueOf(selectedPart.getMax()));
        textMin.setText(String.valueOf(selectedPart.getMin()));

        if (selectedPart instanceof inHouse) {
            inHouseRadio.setSelected(true);
            labelMulti.setText("Machine ID");
            textMulti.setText(Integer.toString(((inHouse) selectedPart).getMachineId()));
        }
        else {
            outSourcedRadio.setSelected(true);
            labelMulti.setText("Company Name");
            textMulti.setText(((outSourced) selectedPart).getCompanyName());
        }

    }
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

    public void onModPart(ActionEvent actionEvent) throws IOException, NumberFormatException {
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
                        inventory.updatePart(inhousePart);
                        inventory.deletePart(inhousePart);

                    }
                    else if (outSourcedRadio.isSelected()) {
                        outSourced outsourcedPart = new outSourced(id, name, price, invText, min, max, inputSource);
                        outsourcedPart.setCompanyName(inputSource);
                        System.out.println(outsourcedPart.getCompanyName());
                        inventory.updatePart(outsourcedPart);
                        inventory.deletePart(outsourcedPart);
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
        viewPart();
    }

}

