/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqu.wis.view;

import cqu.wis.data.UserData.UserDetails;
import cqu.wis.roles.SceneCoordinator;
import cqu.wis.roles.UserDataManager;
import cqu.wis.roles.UserDataValidator;
import cqu.wis.roles.ValidationResponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author Ujjwal Dhakal 12222900
 */
public class PasswordController implements Initializable {

    private SceneCoordinator sc;
    private UserDataManager udm;
    private UserDataValidator udv;
    
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblOldPassword;
    @FXML
    private TextField txtBoxUsername;
    @FXML
    private TextField txtBoxOldPassword;
    @FXML
    private Label lblNewPassword;
    @FXML
    private TextField txtBoxNewPassword;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnClear;
    @FXML
    private TextArea txtAreaMessages;
    @FXML
    private Label lblLoginDetails;
    @FXML
    private Label lblMessages;
    @FXML
    private Button btnExit;
     
    /**
     * Initializes the controller class. Automatically called.
     * 
     * @param url location to resolve relative path for root object, null if not present
     * @param rb resources to localize root object, null if not present
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Dependency injection to controller
     * 
     * @param sc    SceneCoordinator reference to switch scene
     * @param udm   UserDataManager to access and manage user data
     * @param udv   UserDataValidator to coordinate with validity of data
     */
    public void inject(SceneCoordinator sc, UserDataManager udm, UserDataValidator udv) {
        this.sc = sc;
        this.udm = udm;
        this.udv = udv;
    }
    
    /**
     * collects info and change scene to Login Page
     * 
     * @param event click event of Submit button
     */
    @FXML
    private void buttonSubmitOnClick(ActionEvent event) {
        String username = txtBoxUsername.getText();
        String oldPassword = txtBoxOldPassword.getText();
        String newPassword = txtBoxNewPassword.getText();

        // Step 1: Check for all fields present
        ValidationResponse fieldCheck = udv.checkForFieldsPresent(username, oldPassword, newPassword);
        if (!fieldCheck.valid()) {
            txtAreaMessages.setText(fieldCheck.message());
            return;
        }

        // Step 2: Fetch user from DB
        UserDetails user = udm.findUser(username);

        ValidationResponse passwordCheck = udv.checkNewDetails(user, username, oldPassword, newPassword);
        if (!passwordCheck.valid()) {
            txtAreaMessages.setText(passwordCheck.message());
            return;
        }

        // Step 4: Encrypt and update
        String encrypted = UserDataValidator.generateSHA1(newPassword);
        udm.updatePassword(username, encrypted);

        // Step 5: Confirmation
        txtAreaMessages.setText("Password updated successfully. Please login.");
        sc.setScene(SceneCoordinator.SceneKey.LOGIN);
    }

     /**
     * This method clears all the text input and output streams in WIS GUI
     * 
     * @param event click event of the button Clear
     */
    
    @FXML
    private void buttonClearOnClick(ActionEvent event) {
        txtBoxUsername.setText("");
        txtBoxNewPassword.setText("");
        txtBoxOldPassword.setText("");
        txtAreaMessages.setText("");
    }

     /**
     * This method asks for the confirmation to exit 
     * 
     * @param event click event of button Exit
     */
    
    @FXML
    private void buttonExitOnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to close the application?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Platform.exit();    // exits
            }
        });         
    }

}
