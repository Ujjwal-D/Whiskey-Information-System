/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqu.wis.view;

import cqu.wis.data.WhiskeyData;
import cqu.wis.data.WhiskeyData.WhiskeyDetails;
import cqu.wis.roles.WhiskeyDataManager;
import cqu.wis.roles.WhiskeyDataValidator;
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
    
    private WhiskeyDataManager manager; // object
    private WhiskeyDataValidator validator; // object

    /**
     * Called automatically after FXML is loaded.
     * Initializes the WhiskeyData and WhiskeyDataManager.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WhiskeyData data = new cqu.wis.data.WhiskeyData();
        data.connect();
        data.initialise();
        manager = new WhiskeyDataManager(data);
        validator = new WhiskeyDataValidator();
    }

    /**
     * This method moves to next when button is clicked
     * 
     * @param event click event of button
     */
    
    @FXML
    private void buttonNextOnClick(ActionEvent event) {
        try {
            cqu.wis.data.WhiskeyData.WhiskeyDetails next = manager.next();
            txtBoxDistillery.setText(next.distillery());
            txtBoxAge.setText(String.valueOf(next.age()));
            txtBoxRegion.setText(next.region());
            txtBoxPrice.setText(String.valueOf(next.price()));
        } catch (Exception e) {
            txtAreaMessages.setText("Error: " + e.getMessage());
        }
    }



    /**
     * This method moves to previous action when button is clicked
     * 
     * @param event click event of button
     */ 
    
    @FXML
    private void buttonPreviousOnClick(ActionEvent event) {
        try {
            cqu.wis.data.WhiskeyData.WhiskeyDetails previous = manager.previous();
            txtBoxDistillery.setText(previous.distillery());
            txtBoxAge.setText(String.valueOf(previous.age()));
            txtBoxRegion.setText(previous.region());
            txtBoxPrice.setText(String.valueOf(previous.price()));
        } catch (Exception e) {
            txtAreaMessages.setText("Error: " + e.getMessage());
        }
    }


    /**
     * This method displays information of all malts when button is clicked
     * 
     * @param event click event of button
     */
    
    @FXML
    private void buttonAllMaltsOnClick(ActionEvent event) {
        int count = manager.findAllMalts();
        WhiskeyDetails first = manager.first();

        txtBoxDistillery.setText(first.distillery());
        txtBoxAge.setText(String.valueOf(first.age()));
        txtBoxRegion.setText(first.region());
        txtBoxPrice.setText(String.valueOf(first.price()));

        txtAreaMessages.setText("Records found: " + count);
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
        String min = txtBoxMinMaltsInAgeRange.getText();
        String max = txtBoxMaxMaltsInAgeRange.getText();

        var result = validator.checkAgeRange(min, max);
        if (!result.valid()) {
            txtAreaMessages.setText(result.message());
            return;
        }

        // Us result.range().lower() and result.range().upper() for querying
        int lower = result.range().lower();
        int upper = result.range().upper();

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
