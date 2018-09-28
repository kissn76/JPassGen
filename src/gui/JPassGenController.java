package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import passwordgenerator.PasswordGenerator;
import passwordgenerator.PasswordLengthException;

public class JPassGenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox lengthCheckBox;

    @FXML
    private Slider lengthSlider;

    @FXML
    private Spinner<Integer> lengthSpinner;

    @FXML
    private Spinner<?> lowerSpinner;

    @FXML
    private TextField lowerTextField;

    @FXML
    private Spinner<?> upperSpinner;

    @FXML
    private TextField upperTextField;

    @FXML
    private Spinner<?> digitSpinner;

    @FXML
    private TextField digitTextField;

    @FXML
    private Spinner<?> punctuationSpinner;

    @FXML
    private TextField punctuationTextField;

    @FXML
    private Button generateButton;

    @FXML
    private TextField passwordField;

    @FXML
    void generate(ActionEvent event) {
    }

    @FXML
    void initialize() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        int passLength = 32;// passwordGenerator.getDefaultLength();

        lengthSpinner = new Spinner<>(1, 128, 32, 1);

        try {
            passwordField.setText(passwordGenerator.generate());
        } catch (PasswordLengthException e) {
            e.printStackTrace();
        }
    }
}
