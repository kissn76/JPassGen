package gui;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import characterprofiles.CharacterProfile;
import characterprofiles.CharacterProfileController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import passwordgenerator.PasswordGenerator;
import passwordgenerator.PasswordLengthException;

public class JPassGenPanelController {
    private PasswordGenerator passwordGenerator = new PasswordGenerator();
    private String password;
    private final char hiddenPwdChar = '●';
    private String passwordHider;
    private CharacterProfile characterProfile = new CharacterProfile();
    private File characterProfilesFolder = new File("CharacterProfiles");

    ObservableList<CharacterProfile> characterprofiles;

    @FXML
    private Spinner<Integer> lengthSpinner;

    @FXML
    private Slider lengthSlider;

    @FXML
    private ComboBox<CharacterProfile> characterprofileBox;

    @FXML
    private Button characterprofileDeleteBtn;

    @FXML
    private Button characterprofileSaveBtn;

    @FXML
    private Button characterprofileSaveAsNewBtn;

    @FXML
    private CheckBox lowerCheckBox;

    @FXML
    private Spinner<Integer> lowerSpinner;

    @FXML
    private TextField lowerTextField;

    @FXML
    private CheckBox upperCheckBox;

    @FXML
    private Spinner<Integer> upperSpinner;

    @FXML
    private TextField upperTextField;

    @FXML
    private CheckBox digitCheckBox;

    @FXML
    private Spinner<Integer> digitSpinner;

    @FXML
    private TextField digitTextField;

    @FXML
    private CheckBox punctuationCheckBox;

    @FXML
    private Spinner<Integer> punctuationSpinner;

    @FXML
    private TextField punctuationTextField;

    @FXML
    private Button allcharactersButton;

    @FXML
    private Button safecharsButton;

    @FXML
    private Button generateButton;

    @FXML
    private CheckBox unhidPwdField;

    @FXML
    private TextField passwordField;

    @FXML
    private void deleteCharacterProfile() {
        CharacterProfileController characterProfileController = new CharacterProfileController();
        characterProfileController.delete(this.characterProfile);
        this.characterprofileBox.getItems().remove(this.characterProfile);
    }

    @FXML
    private void saveCharacterProfile() {
        String name = this.characterprofileBox.getEditor().getText();
        if (!name.isEmpty()) {
            this.characterProfile.setName(name);
            this.characterProfile.setLowers(this.lowerTextField.getText());
            this.characterProfile.setUppers(this.upperTextField.getText());
            this.characterProfile.setDigits(this.digitTextField.getText());
            this.characterProfile.setPunctuations(this.punctuationTextField.getText());
            CharacterProfileController characterProfileController = new CharacterProfileController();
            try {
                characterProfileController.save(this.characterProfile);
                this.characterprofileBox.getValue().setName(name);
                this.characterprofileBox.getCellFactory().
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void saveAsNewCharacterProfile() {
        String name = this.characterprofileBox.getEditor().getText();
        if (!name.isEmpty()) {
            this.characterProfile.setName(name);
            this.characterProfile.setLowers(this.lowerTextField.getText());
            this.characterProfile.setUppers(this.upperTextField.getText());
            this.characterProfile.setDigits(this.digitTextField.getText());
            this.characterProfile.setPunctuations(this.punctuationTextField.getText());
            CharacterProfileController characterProfileController = new CharacterProfileController();
            try {
                characterProfileController.saveAsNew(this.characterProfile, this.characterProfilesFolder);
                this.characterprofileBox.getItems().add(this.characterProfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    /*
     * Fill texfields with all character types
     */
    private void setAllCharacters(ActionEvent event) {
        this.lowerTextField.setText(this.passwordGenerator.getLOWERALL());
        this.upperTextField.setText(this.passwordGenerator.getUPPERALL());
        this.digitTextField.setText(this.passwordGenerator.getDIGITSALL());
        this.punctuationTextField.setText(this.passwordGenerator.getPUNCTUATIONALL());
    }

    @FXML
    /*
     * Fill texfields with safe character types
     */
    private void setSafeCharacters(ActionEvent event) {
        this.lowerTextField.setText(this.passwordGenerator.getLOWERSAFE());
        this.upperTextField.setText(this.passwordGenerator.getUPPERSAFE());
        this.digitTextField.setText(this.passwordGenerator.getDIGITSSAFE());
        this.punctuationTextField.setText(this.passwordGenerator.getPUNCTUATIONSAFE());
    }

    @FXML
    private void generate(ActionEvent event) {
        // Setup password generator from form
        this.passwordGenerator.setEnabledCharacterTypes(this.lowerCheckBox.isSelected(), this.upperCheckBox.isSelected(), this.digitCheckBox.isSelected(), this.punctuationCheckBox.isSelected());
        this.passwordGenerator.setMinNumberCharacters(this.lowerSpinner.getValue(), this.upperSpinner.getValue(), this.digitSpinner.getValue(), this.punctuationSpinner.getValue());
        this.passwordGenerator.setLOWER(this.lowerTextField.getText());
        this.passwordGenerator.setUPPER(this.upperTextField.getText());
        this.passwordGenerator.setDIGITS(this.digitTextField.getText());
        this.passwordGenerator.setPUNCTUATION(this.punctuationTextField.getText());

        // generate password
        try {
            this.password = this.passwordGenerator.generate(Integer.parseInt(this.lengthSpinner.getEditor().getText()));
            this.passwordHider = passwordHider(this.password.length()); // generate password hider dots
            this.unhidPwdField.setSelected(false);                      // uncheck password field unhider checkbox
            this.passwordField.setText(this.passwordHider);             // add dots to password field
        } catch (PasswordLengthException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        int passLength = this.passwordGenerator.getDefaultLength();
        int maxLength = 256;

        /*
         * Password length
         */
        this.lengthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, maxLength, passLength));
        this.lengthSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                JPassGenPanelController.this.lengthSlider.setValue(newValue);
            }
        });

        this.lengthSlider.setMin(0);
        this.lengthSlider.setMax(maxLength);
        this.lengthSlider.setValue(passLength);
        this.lengthSlider.setShowTickLabels(true);
        this.lengthSlider.setShowTickMarks(true);
        this.lengthSlider.setMajorTickUnit(32);
        this.lengthSlider.setMinorTickCount(3);
        this.lengthSlider.setBlockIncrement(1);
        this.lengthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() < 1) {
                    newValue = 1;
                    JPassGenPanelController.this.lengthSlider.setValue(newValue.intValue());
                }
                JPassGenPanelController.this.lengthSpinner.getEditor().setText(String.valueOf(newValue.intValue()));
            }
        });

        /**
         * Character Profiles
         */
        Map<String, CharacterProfile> charProfiles;
        try {
            charProfiles = new CharacterProfileController().getCharacterProfiles(this.characterProfilesFolder);
            this.characterprofileBox.getItems().addAll(charProfiles.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.characterprofileBox.valueProperty().addListener(new ChangeListener<CharacterProfile>() {
            @Override
            public void changed(ObservableValue<? extends CharacterProfile> observable, CharacterProfile oldValue, CharacterProfile newValue) {
                JPassGenPanelController.this.characterProfile = newValue;
                setCharacterFields();
            }
        });

        /*
         * Lowercase
         */
        this.lowerCheckBox.setSelected(this.passwordGenerator.isEnabledLower());
        this.lowerSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, maxLength));

        /*
         * Uppercase
         */
        this.upperCheckBox.setSelected(this.passwordGenerator.isEnabledUpper());
        this.upperSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, maxLength));

        /*
         * Digit
         */
        this.digitCheckBox.setSelected(this.passwordGenerator.isEnabledDigit());
        this.digitSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, maxLength));

        /*
         * Punctuation
         */
        this.punctuationCheckBox.setSelected(this.passwordGenerator.isEnabledPunctuation());
        this.punctuationSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, maxLength));

        setCharacterFields();

        /*
         * Password
         */
        this.unhidPwdField.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    JPassGenPanelController.this.passwordField.setText(JPassGenPanelController.this.password);
                } else {
                    JPassGenPanelController.this.passwordField.setText(JPassGenPanelController.this.passwordHider);
                }
            }
        });
    }

    private void setCharacterFields() {
        this.lowerTextField.setText(this.characterProfile.getLowers());
        this.upperTextField.setText(this.characterProfile.getUppers());
        this.digitTextField.setText(this.characterProfile.getDigits());
        this.punctuationTextField.setText(this.characterProfile.getPunctuations());
    }

    /*
     * Generate dots for password field to hide.
     */
    private String passwordHider(int length) {
        String pwdHider = "";
        for (int i = 0; i < length; i++) {
            pwdHider += this.hiddenPwdChar;
        }
        return pwdHider;
    }
}
