/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.controller;

import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.model.User;
import ch.bbbaden.casino.bumchums.casino.util.AlertType;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import ch.bbbaden.casino.bumchums.casino.util.Validator;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LB
 */
public class SettingsViewController implements Initializable {

    private MainApp mainApp;
    private Stage stage;
    private User user;
    private DBDefaultUtil db;
    
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField passwordField;
    @FXML
    private JFXTextField firstNameField;
    @FXML
    private JFXTextField lastNameField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new DBDefaultUtil();
    }    
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void handleApplyChangesAction(ActionEvent event) {
        
        String fn = firstNameField.getText();
        String ln = lastNameField.getText();
        String e = emailField.getText();
        String pwd = passwordField.getText();
                
        if (!user.getFirstName().equals(fn) && !fn.trim().isEmpty()) {
            user.setFirstName(fn);
            db.updateUser(user);
            mainApp.showAlert("First Name changed!", "First Name  was changed successfully.", AlertType.INFO, null, "Okay");
        }
        if (!user.getLastName().equals(ln) && !ln.trim().isEmpty()) {
            user.setLastName(ln);
            db.updateUser(user);
            mainApp.showAlert("Last Name changed!", "Last Name was changed successfully.", AlertType.INFO, null, "Okay");
        }
        if (!user.getEmail().equals(e) && !e.trim().isEmpty()) {
            user.setEmail(e);
            db.updateUser(user);
            mainApp.showAlert("Email changed!", "Email was changed successfully.", AlertType.INFO, null, "Okay");
        }
        if (!pwd.trim().isEmpty() || !pwd.equals("")) {
            String alertText = Validator.validatePassword(pwd);
            
            if (alertText.equals("valid")) {
                db.updatePassword(user, pwd);
                mainApp.showAlert("Password changed!", "Password was changed successfully.", AlertType.INFO, null, "Okay");          
            } else {
                mainApp.showAlert("Error changing password", alertText, AlertType.ERROR, null, "Okay");
            }
        }
        
        this.stage.close();
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    private void handleSignOutAction(ActionEvent event) {
        mainApp.showStartView();
        this.stage.close();
    }
    
}
