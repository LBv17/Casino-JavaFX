/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.controller;

import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.User;
import ch.bbbaden.casino.bumchums.casino.util.AlertType;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import ch.bbbaden.casino.bumchums.casino.util.Validator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LB
 */
public class SignUpViewController implements Initializable {

    private double X;
    private double Y;
    private Stage stage;
    private MainApp mainApp;
    
    @FXML
    private JFXButton continueButton;
    @FXML
    private JFXTextField firstNameField;
    @FXML
    private JFXTextField lastNameField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXPasswordField repPasswordField;
    @FXML
    private Pane dragPane;
    @FXML
    private ImageView closeImageView;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Handle dragging the Stage with recursion in the following 3 functions
     * @param event 
     */
    @FXML
    private void handlePaneMouseDragged(MouseEvent event) {
        stage.setX(event.getScreenX() + X);
        stage.setY(event.getScreenY() + Y);
    }

    @FXML
    private void handlePaneDragReleased(MouseDragEvent event) {
        stage.setX(event.getScreenX());
        stage.setY(event.getScreenY());
    }

    @FXML
    private void handlePanePressed(MouseEvent event) {
        X = stage.getX() - event.getScreenX();
        Y = stage.getY() - event.getScreenY();
    }
    
    @FXML
    private void handleCloseClicked(MouseEvent event) {
        stage.close();
    }

    @FXML
    private void handleContinueAction(ActionEvent event) {
        
        String fn = firstNameField.getText();
        String ln = lastNameField.getText();
        String email = emailField.getText();
        String pw = passwordField.getText();
        String repPw = repPasswordField.getText();
                
        String alertText = "";
        
        if (!Validator.validateName(fn)) {
            alertText += "Invalid first name!\n";
            firstNameField.setText("");
        }
        if (!Validator.validateName(ln)) {
            alertText += "Invalid last name!\n";
            lastNameField.setText("");
        }
        if (!Validator.validateEmail(email)) {
            alertText += "Invalid email\n";
            emailField.setText("");
        }
        if (pw.equals(repPw)) {
            alertText += "Passwords don't match!\n";
            repPasswordField.setPromptText("");
        }
        
        alertText += Validator.validatePassword(pw);
        
        if (alertText.endsWith("valid")) {
            DBDefaultUtil db = new DBDefaultUtil();
       
            if (db.getUser(email) != null) {
            // User already exists
            }
  
            // Create user in db
            db.addNewUser(email, fn, ln, pw);
            
            // show alert and say to sign in
            mainApp.showAlert("Sign Up Successful!", "You can sign in now.", AlertType.INFO, null, "Okay");
            this.stage.close();
            
        } else {
            mainApp.showAlert("Sign Up Error!", alertText, AlertType.ERROR, null, "Okay");
        }

    }
}
