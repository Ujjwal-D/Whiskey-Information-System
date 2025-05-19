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
import java.util.Optional;
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
public class LoginController implements Initializable {

    private SceneCoordinator sc;
    private UserDataManager udm;
    private UserDataValidator udv;
    
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblPassword;
    @FXML
    private TextField txtBoxUsername;
    @FXML
    private TextField txtBoxPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnChangePassword;
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
     * Takes action when login button is clicked in GUI
     * 
     * @param event click event of Login button
     */
    @FXML
    private void buttonLoginOnClick(ActionEvent event) {
        String username = txtBoxUsername.getText();
        String password = txtBoxPassword.getText();

        // Step 1: Check for field presence
        ValidationResponse fieldCheck = udv.checkForFieldsPresent(username, password);
        if (!fieldCheck.valid()) {
            txtAreaMessages.setText(fieldCheck.message());
            return;
        }

        // Step 2: Retrieve user from database
        UserDetails user = udm.findUser(username);

        // Step 3: Credential validation
        ValidationResponse credentialCheck = udv.checkCurrentDetails(user, username, password);
        txtAreaMessages.setText(credentialCheck.message());

        // Step 4: If valid and NOT default password, move to query scene
        if (credentialCheck.valid() && !"password".equals(user.password())) {
            sc.setScene(SceneCoordinator.SceneKey.QUERY);
        }
    }

    /**
     * Changes the scene to change password
     * 
     * @param event event of change password button click 
     */
    @FXML
    private void buttonChangePasswordOnClick(ActionEvent event) {
        txtAreaMessages.setText("");
        sc.setScene(SceneCoordinator.SceneKey.PASSWORD);
    }

     /**
     * This method clears all the text input and output streams in WIS GUI
     * 
     * @param event click event of the button clear
     */
    
    @FXML
    private void buttonClearOnClick(ActionEvent event) {
        txtBoxUsername.setText("");
        txtBoxPassword.setText("");
        txtAreaMessages.setText("");
    }

     /**
     * This method asks for the confirmation to exit 
     * 
     * @param event click event of button exit
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
