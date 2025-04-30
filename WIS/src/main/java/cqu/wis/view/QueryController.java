/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqu.wis.view;

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
 * @author Ujjwal
 */
public class QueryController implements Initializable {

    @FXML
    private Label lblOutput;
    @FXML
    private Label lblMessages;
    @FXML
    private Label lblDistillery;
    @FXML
    private Label lblAge;
    @FXML
    private Label lblRegion;
    @FXML
    private Label lblPrice;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnPrevious;
    @FXML
    private TextField txtBoxDistillery;
    @FXML
    private TextField txtBoxAge;
    @FXML
    private TextField txtBoxRegion;
    @FXML
    private TextField txtBoxPrice;
    @FXML
    private TextField txtBoxMaltsFromRegion;
    @FXML
    private TextField txtBoxMinMaltsInAgeRange;
    @FXML
    private Button btnAllMalts;
    @FXML
    private Button btnMaltsFromRegion;
    @FXML
    private Button btnMaltsInAgeRange;
    @FXML
    private Label lblInput;
    @FXML
    private Button btnClear;
    @FXML
    private TextArea txtAreaMessages;
    @FXML
    private Button btnExit;
    @FXML
    private TextField txtBoxMaxMaltsInAgeRange;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * This method moves to next when button is clicked
     * 
     * @param event click event of button
     */
    
    @FXML
    private void buttonNextOnClick(ActionEvent event) {
        this.txtAreaMessages.setText("Next Button: Under Development");
    }

    /**
     * This method moves to previous action when button is clicked
     * 
     * @param event click event of button
     */ 
    
    @FXML
    private void buttonPreviousOnClick(ActionEvent event) {
        this.txtAreaMessages.setText("Previous Button: Under Development");
    }

    /**
     * This method displays information of all malts when button is clicked
     * 
     * @param event click event of button
     */
    
    @FXML
    private void buttonAllMaltsOnClick(ActionEvent event) {
        this.txtAreaMessages.setText("All Malts Button: Under Development");
    }

    /**
     * This method displays malts info according to region when button is clicked
     * 
     * @param event click event of button
     */
    
    @FXML
    private void buttonMaltsFromRegionOnClick(ActionEvent event) {
        this.txtAreaMessages.setText("Malts From Region Button: Under Development");
    }

    /**
     * This method displays malts info within specified range when button is clicked
     * 
     * @param event click event of button
     */
    
    @FXML
    private void buttonMaltsInAgeRangeOnClick(ActionEvent event) {
        this.txtAreaMessages.setText("Malts In Age Range Button: Under Development");
    }

    /**
     * This method clears all the text input and output streams in GUI
     * 
     * @param event click event of the button
     */
    
    @FXML
    private void buttonClearOnClick(ActionEvent event) {
        this.txtBoxDistillery.setText("");  // clears textbox
        this.txtBoxAge.setText("");  // clears textbox   
        this.txtBoxRegion.setText("");  // clears textbox        
        this.txtBoxPrice.setText("");  // clears textbox
        this.txtBoxMaltsFromRegion.setText(""); // clears textbox
        this.txtBoxMinMaltsInAgeRange.setText("");  // clears textbox
        this.txtBoxMaxMaltsInAgeRange.setText("");  // clears textbox
        this.txtAreaMessages.setText("");   // clears text area
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
