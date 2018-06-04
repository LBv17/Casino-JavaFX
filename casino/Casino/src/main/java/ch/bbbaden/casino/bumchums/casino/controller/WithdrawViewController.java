/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.controller;

import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.util.AlertType;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LB
 */
public class WithdrawViewController implements Initializable {

    @FXML
    private ImageView closeView;
    @FXML
    private JFXTextField withdrawField;
    private MainApp mainApp;
    private Stage stage;
    private Player player;
    private DBDefaultUtil db;

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

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    @FXML
    private void handleCloseAction(MouseEvent event) {
        this.stage.close();
    }

    @FXML
    private void handleWithdrawAction(ActionEvent event) {
        String amount = withdrawField.getText();
        int withdrawAmount;
        
        try {
            withdrawAmount = Integer.parseInt(amount);
            if (withdrawAmount < db.getAccountBalance(player.getEmail())) {
                db.updateAccountBalance(player.getEmail(), 0-withdrawAmount);  
                Platform.runLater(() -> {
                    mainApp.showAlert("Withdraw Successful", "Your balance has been updated!", AlertType.INFO, player, "Okay");
                });
                mainApp.getPrimaryStage().close();
                mainApp.showMenuView(player);
                stage.close();
            } else {
                mainApp.showAlert("Error", "You don't have that many credits!", AlertType.ERROR, player, "Okay");
                stage.close();
            }           
        } catch (Exception e) {
            System.out.println(e);
            mainApp.showAlert("Error", e.getMessage(), AlertType.ERROR, player, "Okay");
        }  
    }
    
}
