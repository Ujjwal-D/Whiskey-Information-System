/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqu.wis.view;

import cqu.wis.data.WhiskeyData;
import cqu.wis.data.WhiskeyData.WhiskeyDetails;
import cqu.wis.roles.SceneCoordinator;
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
 * @author Ujjwal Dhakal 12222900
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
    private SceneCoordinator coordinator; //object

    /**
     * Initializes the controller class. Automatically called.
     * 
     * @param url location to resolve relative path for root object, null if not present
     * @param rb resources to localize root object, null if not present
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization
    }
    
    /**
     * Injects dependencies into the controller.
     * @param sc scene coordinator
     * @param wdm data manager
     * @param wdv data validator
     */
    public void inject(SceneCoordinator sc, WhiskeyDataManager wdm, WhiskeyDataValidator wdv) {
        this.coordinator = sc;
        this.manager = wdm;
        this.validator = wdv;
    }
    
    /**
     * This method moves to next when button is clicked
     * 
     * @param event click event of button Next
     */
    
    @FXML
    private void buttonNextOnClick(ActionEvent event) {
        try {
            WhiskeyDetails next = manager.next();
            if (next == null) {
                txtAreaMessages.setText("No records loaded. Please load records first.");
                return;
            }
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
     * @param event click event of button Previous
     */ 
    
    @FXML
    private void buttonPreviousOnClick(ActionEvent event) {
        try {
            WhiskeyDetails previous = manager.previous();
            if (previous == null) {
                txtAreaMessages.setText("No records loaded. Please load records first.");
                return;
            }
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
     * @param event click event of button All Malts
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
     * @param event click event of button Malts from Region
     */
    
    @FXML
    private void buttonMaltsFromRegionOnClick(ActionEvent event) {
        String region = txtBoxMaltsFromRegion.getText();

        var response = validator.checkRegion(region);
        if (!response.valid()) {
            txtAreaMessages.setText(response.message());
            return;
        }

        int count = manager.findMaltsFromRegion(region);
        if (count == 0) {
            txtAreaMessages.setText("No records found for region: " + region);
            return;
        }

        WhiskeyDetails result = manager.first();
        txtBoxDistillery.setText(result.distillery());
        txtBoxAge.setText(String.valueOf(result.age()));
        txtBoxRegion.setText(result.region());
        txtBoxPrice.setText(String.valueOf(result.price()));

        txtAreaMessages.setText("Records found for region '" + region + "': " + count);
    }


    /**
     * This method displays malts info within specified range when button is clicked
     * 
     * @param event click event of button Malts in Age Range
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

        int lower = result.range().lower();
        int upper = result.range().upper();

        int count = manager.findMaltsInAgeRange(lower, upper);
        if (count == 0) {
            txtAreaMessages.setText("No records found in age range " + lower + "–" + upper + ".");
            return;
        }

        WhiskeyDetails resultDetails = manager.first();
        txtBoxDistillery.setText(resultDetails.distillery());
        txtBoxAge.setText(String.valueOf(resultDetails.age()));
        txtBoxRegion.setText(resultDetails.region());
        txtBoxPrice.setText(String.valueOf(resultDetails.price()));

        txtAreaMessages.setText("Records found in age range " + lower + "–" + upper + ": " + count);
    }

    /**
     * This method clears all the text input and output streams in GUI
     * 
     * @param event click event of the button Clear
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
