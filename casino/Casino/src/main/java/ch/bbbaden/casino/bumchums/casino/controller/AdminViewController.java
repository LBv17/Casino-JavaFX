/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.controller;

import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.Admin;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LB
 */
public class AdminViewController implements Initializable {

    private MainApp mainApp;
    private Stage primaryStage;
    private Admin admin;
    
    @FXML
    private ImageView closeImageView;
    @FXML
    private Pane dragPane;
    @FXML
    private ImageView minimizeImageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setUser(Admin admin) {
        this.admin = admin;
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void showGameStatisticsAction(MouseEvent event) {
        mainApp.showGameStatsView(admin);
    }

    @FXML
    private void showPlayerStatisticsAction(MouseEvent event) {
        mainApp.showPlayerStatsView(admin);
    }

    @FXML
    private void handleCloseAction(MouseEvent event) {
        mainApp.showStartView();
    }

    @FXML
    private void handleMinimizeAction(MouseEvent event) {
        mainApp.getPrimaryStage().setIconified(true);
    }

    @FXML
    private void showAdminSettingsAction(MouseEvent event) {
        mainApp.showAdminSettings(admin);
    }
    
}
