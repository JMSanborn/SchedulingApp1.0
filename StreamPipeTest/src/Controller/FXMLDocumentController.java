package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class FXMLDocumentController implements Initializable {


    @FXML
    private TextArea textAreaUI;

    public static TextArea staticTxtArea;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        // these will be redirected to textArea on GUI

        System.err.println("@@@@ERROR: This is error");
        System.out.println("####OUTPUT : THIS IS ERROR");
        //generating an exception to print on console
        try
        {
            int x  = 5/0;

        }
        catch(Exception ex)
        {
            ex.printStackTrace();

            // System.out.println(ex.getMessage());

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        staticTxtArea = textAreaUI;
    }

}
