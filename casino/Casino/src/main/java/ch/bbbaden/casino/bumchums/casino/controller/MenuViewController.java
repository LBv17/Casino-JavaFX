/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.controller;

import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.model.User;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
public class MenuViewController implements Initializable {

    private double X;
    private double Y;
    private MainApp mainApp;
    private Stage primaryStage;
    private Player player;
    private DBDefaultUtil db = new DBDefaultUtil();
    
    @FXML
    private Pane dragPane;
    @FXML
    private ImageView closeImageView;
    @FXML
    private ImageView minimizeImageView;
    @FXML
    private JFXButton rechargeButton;
    @FXML
    private Label accountBalanceLabel;
    @FXML
    private JFXButton withdrawButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setUser(Player player) {
        this.player = player;
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void loadBalance() {
        int accountBalance = db.getAccountBalance(player.getEmail());
        accountBalanceLabel.setText(String.valueOf(accountBalance));
        
    }
    
    /**
     * Handle dragging the Stage with recursion in the following 3 functions
     * @param event 
     */
    @FXML
    private void handlePaneMouseDragged(MouseEvent event) {
        mainApp.getPrimaryStage().setX(event.getScreenX() + X);
        mainApp.getPrimaryStage().setY(event.getScreenY() + Y);
    }

    @FXML
    private void handlePaneDragReleased(MouseDragEvent event) {
        mainApp.getPrimaryStage().setX(event.getScreenX());
        mainApp.getPrimaryStage().setY(event.getScreenY());
    }

    @FXML
    private void handlePanePressed(MouseEvent event) {
        X = mainApp.getPrimaryStage().getX() - event.getScreenX();
        Y = mainApp.getPrimaryStage().getY() - event.getScreenY();
    }

    @FXML
    private void handleCloseClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleMinimizeClicked(MouseEvent event) {
        mainApp.getPrimaryStage().setIconified(true);
    }

    @FXML
    private void openSettings(MouseEvent event) {
        mainApp.showPlayerSettings(player);
    }

    @FXML
    private void showSicBoAction(MouseEvent event) {
        mainApp.showSicBo(player);
    }

    @FXML
    private void handleRechargeAction(ActionEvent event) {
        mainApp.showRechargeView(player);
    }

    @FXML
    private void handleWithdrawAction(ActionEvent event) {
        mainApp.showWithdrawView(player);
    }

    @FXML
    private void showYatzyAction(MouseEvent event) {
        mainApp.showYatzy(player);
    }

    @FXML
    private void showBingoAction(MouseEvent event) {
        mainApp.showBingo(player);
    }

    @FXML
    private void showBaccaraAction(MouseEvent event) {
        mainApp.showBaccara(player);
    }

    @FXML
    private void showBlackjackAction(MouseEvent event) {
        mainApp.showBlackJack(player);
    }
    
}
