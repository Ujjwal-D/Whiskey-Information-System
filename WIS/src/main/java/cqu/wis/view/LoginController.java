/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqu.wis.view;

import cqu.wis.roles.SceneCoordinator;
import cqu.wis.roles.UserDataManager;
import cqu.wis.roles.UserDataValidator;
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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void inject(SceneCoordinator sc, UserDataManager udm, UserDataValidator udv) {
        this.sc = sc;
        this.udm = udm;
        this.udv = udv;
    }
    
    @FXML
    private void buttonLoginOnClick(ActionEvent event) {
        sc.setScene(SceneCoordinator.SceneKey.QUERY);
    }

    @FXML
    private void buttonChangePasswordOnClick(ActionEvent event) {
        sc.setScene(SceneCoordinator.SceneKey.PASSWORD);
    }

     /**
     * This method clears all the text input and output streams in WIS GUI
     * 
     * @param event click event of the button
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
     * @param event click event of button
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
